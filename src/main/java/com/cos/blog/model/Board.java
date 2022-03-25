package com.cos.blog.model;

import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Board {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
	private int id;
	
	@Column(nullable = false , length = 100)
	private String title;
	
	@Lob		// Lob = large object  (대용량 데이터)
	private String content;	// 섬머노트 라이브러리 <html>태그가 섞여서 디자인이 됨.
	
	// @ColumnDefault("0")
	private int count;	// 조회수
	
	@ManyToOne(fetch = FetchType.EAGER)	// userId랑 user랑 연관관계를 만들어주기 위해 @ManyToOne 씀
	@JoinColumn(name="userId")			// joinColumn(name="") DB에 만들어질떄 userId로 만들어짐
	private User user; 					// DB는 오브젝트를 저장할 수 없다. 그래서 FK 사용, 자바는 오브젝트를 저장할 수 있다.
										// ManyToOne 기본패치전략은 EAGER임 (user정보는 한건밖에 없기때문에)
										// JPA(ORM)을 이용하면 Object를 그대로 저장가능
																					// 참조관계는 다 지우는거(cascadeType.Remove)
	@OneToMany(mappedBy = "board" , fetch = FetchType.EAGER, cascade = CascadeType.REMOVE )	// OneToMany는 기본 패치전략이 lazy임
	@JsonIgnoreProperties({"board"})		// 이 파라미터는 무한(순환) 참조를 막기위해 쓰는 어노테이션
	@OrderBy("id desc")
	private List<Reply> replys;		// 그 이유는 답변은 많이 달릴 수 있어서 필요하면 들고오고 안필요하면 안들고올게 라는 전략
									// 하지만 댓글 기능이 필요하니까 EAGER로 수정
									// board를 select할때 join문을 통해 값을 얻기위해 씀
									// mappedby 연관관계의 주인이 아니다 (FK가 아님) DB에 칼럼을 만들지 마세요란 뜻
	
	@CreationTimestamp
	private Timestamp creatDate;
}
