package com.herokuapp.bcsboot2.domain.posts;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostsRepository extends JpaRepository<Posts, Long> {
	//save(), findAll(),수정은 엔티티의 값만수정하면 DB값도 연동된다, delete() 
	
	//쿼리가 필요하지 않다. 단, findBy로 시작하고, keyword 변수와 이름이 동일하면, 쿼리가 자동으로 만들어진다.(스프링-jpa 데이터처리 규칙)
	Page<Posts> findByTitleContaining(String keyword, Pageable pageable);

	
}