package com.herokuapp.bcsboot2.domain.posts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;



@SpringBootTest//스프링부트에 Junit5=주피터가 포함되어서 구현된다.
class PostsRepositoryTests {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	PostsRepository postRepository;
	
	
	
	@AfterEach
	void tearDown() throws Exception {
		postRepository.deleteAll();
	}

	@Test
	void test() {
		postRepository.save(
				Posts.builder()
				.title("게시글 제목")
				.content("게시글 내용")
				.author("user")
				.build()
				);
		List<Posts> postsList = postRepository.findAll();
		Posts posts = postsList.get(0);
		posts.toString();
		logger.debug("등록된 레코드 수 "+postRepository.count());
		logger.info("디버그 "+posts.toString());
	}

}
