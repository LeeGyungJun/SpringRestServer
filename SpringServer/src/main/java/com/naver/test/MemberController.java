package com.naver.test;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.naver.test.dto.MemberDTO;
import com.naver.test.service.MemberService;

@RestController
@RequestMapping(value = "/member")
public class MemberController {
	
	@Autowired
	MemberService memberService;

	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public Map<String, Object> login(MemberDTO memberDto){
		return memberService.login(memberDto);
	}
	
	//회원 가입
	@RequestMapping(value = "/join", method = RequestMethod.POST)
	public Map<String, Object> join(MemberDTO memberDto, HttpServletRequest request){
		return memberService.join(memberDto, request);
	}
	
	//이메일 중복체크
	@RequestMapping(value = "/emailcheck", method = RequestMethod.POST)
	public Map<String, Object> emailcheck(MemberDTO memberDto){
		return memberService.emailcheck(memberDto.getEmail());
	}

	//닉네임 중복체크
	@RequestMapping(value = "/nicknamecheck", method = RequestMethod.POST)
	public Map<String, Object> nickcheck(MemberDTO memberDto){
		return memberService.nickcheck(memberDto.getNickname());
	}

}
