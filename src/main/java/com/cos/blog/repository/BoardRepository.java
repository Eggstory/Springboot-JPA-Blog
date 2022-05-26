package com.cos.blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cos.blog.model.Board;


public interface BoardRepository extends JpaRepository<Board, Integer> {	
	
	// 조회수 증가
	@Modifying
	@Query("update Board b set b.count = b.count + 1 where b.id = :id")
	int updateCount(int id);

}
