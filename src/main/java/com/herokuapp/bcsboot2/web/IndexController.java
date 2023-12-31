package com.herokuapp.bcsboot2.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.herokuapp.bcsboot2.config.auth.LoginUser;
import com.herokuapp.bcsboot2.config.auth.dto.SessionUser;
import com.herokuapp.bcsboot2.domain.posts.Posts;
import com.herokuapp.bcsboot2.service.posts.FileService;
import com.herokuapp.bcsboot2.service.posts.PostsService;
import com.herokuapp.bcsboot2.web.dto.FileDto;
import com.herokuapp.bcsboot2.web.dto.PostsDto;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor//final 매개변수가 있는 생성자메소드가 자동 생성된다
@Controller//일반컨트롤러는 반환값으로 출력할 페이지를 지정한다
public class IndexController {
	//로그 출력 객체생성
	private Logger logger = LoggerFactory.getLogger(getClass());
	private final PostsService postsService;//생성자로 주입
	private final FileService fileService;//생성자로 주입
	
	@GetMapping("posts/update/{id}")
	public String postsUpdate(@PathVariable Long id, Model model) {
		PostsDto dto = postsService.postsOne(id);//1개의 레코드만 가져온다.
		model.addAttribute("post",dto);//모델객체에 담아서 mustache로 보낸다.
		if(dto.getFileId() != null) {
			//단일 첨부파일 처리는 이후 수업에서 작업(아래)
			FileDto fileDto = fileService.getFile(dto.getFileId());
			model.addAttribute("OrigFilename", fileDto.getOrigFilename());
		}
		return "posts/posts-update";
	}
	
	@GetMapping("/posts/read/{id}")//패스경로에 id값이 들어갔다. @PathVariable 사용해서 자바코드에서 사용
	public String postsRead(@PathVariable Long id, Model model) {
		PostsDto dto = postsService.postsOne(id);//1개의 레코드만 가져온다.
		model.addAttribute("post",dto);//모델객체에 담아서 mustache로 보낸다.
		if(dto.getFileId() != null) {
			//단일 첨부파일 처리는 이후 수업에서 작업(아래)
			FileDto fileDto = fileService.getFile(dto.getFileId());
			model.addAttribute("OrigFilename", fileDto.getOrigFilename());
		}
		return "posts/posts-read";
	}
	
	@GetMapping("/posts/save")//Url주소와 posts-save.mustache를 매핑시킨다.
	public String postsSave() {
		return "posts/posts-save";
	}
	
	//@GetMapping("/posts")//전체게시물 Read
	@GetMapping("/")//접근 Api Url을 도메인 루트로 변경한다.
	public String postList(@RequestParam(value="keyword", defaultValue="")String keyword,@PageableDefault(size=5,sort="id",direction=Sort.Direction.DESC) Pageable pageable, Model model,@LoginUser SessionUser user) {
		
		if(user!=null)
		{
			model.addAttribute("sessionUserName",user.getName());
			model.addAttribute("sessionRoleName","ROLE_ADMIN".equals(user.getRole())?"admin":null);
		}
		Page<Posts> postsList = postsService.postsList(keyword,pageable);//오버로드 된 검색 키워드와 함께 매개변수로 넘겨서 작성한 검색 함수로 넘어간다.
		model.addAttribute("postsList", postsList);//게시글목록 5개
		model.addAttribute("currPage", postsList.getPageable().getPageNumber());//현재페이지번호
		model.addAttribute("pageIndex", postsList.getTotalPages());//전체페이지개수
		model.addAttribute("prevCheck", postsList.hasPrevious());//이전페이지 있는지 체크
		model.addAttribute("previous", pageable.previousOrFirst().getPageNumber());//이전페이지번호 사용
		model.addAttribute("nextCheck", postsList.hasNext());//다음페이지 있는지 체크
		model.addAttribute("next", pageable.next().getPageNumber());//다음페이지번호 사용
		//return "posts/post-list";//출력할 페이지명 posts폴더/post-list.mustache파일(html디자인템플릿)
		//index.mustache 파일이 templates 폴더 최상위에 있기 때문에 경로없이 파일명을 사용한다.
		return "index";//index.mustache에서 확장자가 생략된다.
	}
}