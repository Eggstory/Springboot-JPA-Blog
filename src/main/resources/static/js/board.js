let index = {
	init: function() {
		$("#btn-save").on("click",()=>{		// function(){}, ()=>{} 라고 쓴이유는
			this.save();					// 코드줄이려는게 아니고 this를 바인딩하기 위해서
		});
		$("#btn-delete").on("click",()=>{		// function(){}, ()=>{} 라고 쓴이유는
			this.deleteById();					// 코드줄이려는게 아니고 this를 바인딩하기 위해서
		});
		$("#btn-update").on("click",()=>{		// function(){}, ()=>{} 라고 쓴이유는
			this.update();					// 코드줄이려는게 아니고 this를 바인딩하기 위해서
		});
	},
	
/*	
	// 람다식말고 꼭 function으로 써야겠다 싶으면 아래와같이 쓰기
	let _this=this;
	init: function() {
		$("#btn-save").on("click", function() {
			_this.save();
		});
	},
	
*/	
	
	save: function() {
		//alert('user의 save함수 호출됨');
		let data = {
				title: $("#title").val(),
				content: $("#content").val()
		};
		
		//console.log(data);
		
		// ajax 호출시 default가 비동기 호출
		// ajax 통신을 이용해서 3개의 데이터를 json으로 변경하여 insert 요청
		// ajax가 통신을 성공하고 서버가 json을 리턴해주면 자동으로 자바 오브젝트로 변환해줌
		$.ajax({
			type:"POST",
			url:"/api/board",
			data: JSON.stringify(data),	//http body 데이터
			contentType:"application/json; charset=utf-8",	//body 데이터가 어떤 타입인지(MIME)
			dataType: "json"	// 요청을 서버로해서 응답이 왔을 때 기본적으로 모든것이 문자열 (응답 데이터 타입 지정)
								// (생긴게 json이라면 => javascript 오브젝트로 변경)
		}).done(function(resp){
			alert("글쓰기가 완료되었습니다.");
			//console.log(resp);
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
		
	}, 	
	
	deleteById: function() {
		let id = $("#id").text();

		$.ajax({
			type:"DELETE",
			url:"/api/board/"+id,
			dataType: "json"
		}).done(function(resp){
			alert("삭제가 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
		
	},
	
	update: function() {
		let id = $("#id").val();
		
		let data = {
				title: $("#title").val(),
				content: $("#content").val()
		};
		
		$.ajax({
			type:"PUT",
			url:"/api/board/"+id,
			data: JSON.stringify(data),
			contentType:"application/json; charset=utf-8",
			dataType: "json"	

		}).done(function(resp){
			alert("글수정이 완료되었습니다.");
			location.href="/";
		}).fail(function(error){
			alert(JSON.stringify(error));
		});
		
	}
}

index.init();