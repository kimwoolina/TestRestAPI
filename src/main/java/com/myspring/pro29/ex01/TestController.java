package com.myspring.pro29.ex01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/*")
public class TestController {
	static Logger logger = LoggerFactory.getLogger(TestController.class);

	@RequestMapping("/hello")
	public String hello() {
		return "Hello REST!!";
	}

	@RequestMapping("/member")
	public MemberVO member() {
		MemberVO vo = new MemberVO();
		vo.setId("hong");
		vo.setPwd("1234");
		vo.setName("홍길동");
		vo.setEmail("hong@test.com");
		return vo;
	}

	@RequestMapping("/membersList")
	public List<MemberVO> listMembers() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		for (int i = 0; i < 10; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("hong" + i);
			vo.setPwd("123" + i);
			vo.setName("홍길동" + i);
			vo.setEmail("hong" + i + "@test.com");
			list.add(vo);
		}
		return list;
	}

	@RequestMapping("/membersMap")
	public Map<Integer, MemberVO> membersMap() {
		Map<Integer, MemberVO> map = new HashMap<Integer, MemberVO>();
		for (int i = 0; i < 10; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("hong" + i);
			vo.setPwd("123" + i);
			vo.setName("홍길동" + i);
			vo.setEmail("hong" + i + "@test.com");
			map.put(i, vo);
		}
		return map;
	}

	// {}브라우저 요청시 전달된 값 > localhost:8888/pro29/test/notice/112 > 112출력
	@RequestMapping(value = "/notice/{num}", method = RequestMethod.GET)
	public int notice(@PathVariable("num") int num) throws Exception {
		return num;
	} // num이라는 변수에 값을 넣어서 브라우저로 전송

	
	//브라우저에서 전송된 값이 자동으로 member객체에 셋팅됨  
	//Homecontroller에 기존의 home 메소드는 주석 처리하고 수정
	//views 밑에 JSONTest.jsp 파일 생성 
	//localhost:8888/pro29/로 접속
	//lib폴더 만들고 jstl라이브러리 파일 추가 할 것
	@RequestMapping(value = "/info", method = RequestMethod.POST)
	public void modify(@RequestBody MemberVO vo) {
		logger.info(vo.toString());
	}

	//10개의 vo객체를 JSON 객체로 전송
	//localhost:8888/pro29/test/memberList2
	//개발자 도구 > Network > memberList2의 Headers > Status Code 500 확인됨
	@RequestMapping("/membersList2")
	public ResponseEntity<List<MemberVO>> listMembers2() {
		List<MemberVO> list = new ArrayList<MemberVO>();
		for (int i = 0; i < 10; i++) {
			MemberVO vo = new MemberVO();
			vo.setId("lee" + i);
			vo.setPwd("123" + i);
			vo.setName("이순신" + i);
			vo.setEmail("lee" + i + "@test.com");
			list.add(vo);
		}
		return new ResponseEntity(list, HttpStatus.INTERNAL_SERVER_ERROR); //500에러 상태코드도 전송
	}

	//ResponseEntity로 반환되는 데이터의 type을 지정해줄 수 있다.
	//localhost:8888/pro29/test/res3
	@RequestMapping(value = "/res3")
	public ResponseEntity res3() {
		HttpHeaders responseHeaders = new HttpHeaders(); //원하는 데이터 형식으로 지정 위해 이용
		responseHeaders.add("Content-Type", "text/html; charset=utf-8");
		String message = "<script>";
		message += " alert('새 회원을 등록합니다.');";
		message += " location.href='/pro29/test/membersList2'; ";
		message += " </script>";
		return new ResponseEntity(message, responseHeaders, HttpStatus.CREATED);
	} //반환하는 데이터(message=자바스크립트 문자열)의 타입을 responseHeaders에 지정된 text/html으로 지정하여 반환한다.
	// 
	
}