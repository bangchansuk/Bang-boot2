package com.herokuapp.bcsboot2.service.simple_users;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.herokuapp.bcsboot2.domain.posts.File;
import com.herokuapp.bcsboot2.domain.posts.FileRepository;
import com.herokuapp.bcsboot2.domain.simple_users.SimpleUsers;
import com.herokuapp.bcsboot2.domain.simple_users.SimpleUsersRepository;
import com.herokuapp.bcsboot2.web.dto.FileDto;
import com.herokuapp.bcsboot2.web.dto.SimpleUsersDto;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor//final 매개변수가 있는 생성자메소드가 자동 생성된다..zz
@Service//서비스 객체로 만든다
public class SimpleUsersService {
	//final로 DB 핸들링한 객체 생성
	private final SimpleUsersRepository simpleUsersRepository;
	@Transactional//저장:Create
	public Long save(SimpleUsersDto requestDto) {
		return simpleUsersRepository.save(requestDto.toEntity()).getId();
	}
	@Transactional//읽기:1명회원 Read:username 으로 검색해야한다.
	public SimpleUsersDto findByName(String username) {
		SimpleUsers entity = simpleUsersRepository.findByName(username);
		return new SimpleUsersDto(entity);
	}
	@Transactional//읽기:전체회원 Read
	public Page<SimpleUsers> usersList(Pageable pageable) {
		return simpleUsersRepository.findAll(pageable);
	}
	@Transactional//읽기:전체회원 Read 오버 로드 기능
	public Page<SimpleUsers> usersList(String ketword,Pageable pageable) {
		return simpleUsersRepository.findByUsernameContaining(ketword,pageable);
	}
	@Transactional//수정:Update는 엔티티의 값만수정하면 레포지토리 없이 DB값도 연동된다
	public Long update(Long id, SimpleUsersDto requestDto) {
		SimpleUsers simpleUsers = simpleUsersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
		simpleUsers.update(requestDto.getUsername(), requestDto.getPassword(), requestDto.getRole(), requestDto.getEnabled());
		return id;
	}
	@Transactional//삭제:Delete 1명만 삭제됨
	public void delete(Long id) {
		SimpleUsers simpleUsers = simpleUsersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));
		simpleUsersRepository.delete(simpleUsers);
	}
}