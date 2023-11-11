package com.herokuapp.bcsboot2.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc//API(/hello)경로로 접근하기 위한 샘플 MVC가 필요하다.
@SpringBootTest//스프링부트에 Junit5=주피터가 포함되어서 구현된다.
class HelloControllerTests {

	@Autowired
	private HelloComtroller helloComtroller;
	
	@Autowired
	private MockMvc mockMvc;
	
	@BeforeAll//JUnit실행 전전처리
	static void setUpBeforeClass() throws Exception {
		System.out.println("BeforeEach1");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("AfterEach1");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("BeforeEach");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("AfterEach");
	}


	@BeforeEach//JUnit실행 전처리 중략…
	@Test
	void test() {
	//fail("Not yet implemented");
	//assert로 시작하는 에러 검출용 메소드를 사용
	//assertEquals(2, 2);//기본메소드, 좀더 향상된 기능의 assertThat을 사용
	//결과출력방식: assertThat(비교대상 값).비교매소드(비교기준값)
	assertThat(10).isEqualTo(10);
	}
	
	@Test
	void hello()throws Exception {
		helloComtroller.hello();
		
		mockMvc.perform(
					get("/")
				)
		.andExpect(status().isOk())
		.andExpect(content().string("hello"));
	}
}
