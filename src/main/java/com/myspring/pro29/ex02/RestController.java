package com.myspring.pro29.ex02;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RestController {
	
	//ResponseBody 이용해 선택적으로 REST기능 메소드에 적용
	
	//localhost:8888/pro29/res1
	@RequestMapping(value = "/res1")
	@ResponseBody
	public Map<String, Object> res1() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", "hong");
		map.put("name", "김우린");
		return map;
	} // 호출 후 데이터(map)만 전송
	
	//localhost:8888/pro29/res2
	@RequestMapping(value = "/res2")
	public ModelAndView res2() {
		return new ModelAndView("home");
	} //호출 후 JSP파일 (home.jsp) 보여줌
	
}
