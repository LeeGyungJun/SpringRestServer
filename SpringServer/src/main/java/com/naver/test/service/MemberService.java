package com.naver.test.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.naver.test.dto.MemberDTO;

public interface MemberService {
	//로그인
	public Map<String, Object> login(MemberDTO memberDto);
	
	//회원 가입
	public Map<String, Object> join(MemberDTO memberDto, HttpServletRequest request);
	
	//이메일 중복체크
	public Map<String, Object> emailcheck(String email);
	
	//닉네임 중복체크
	public Map<String, Object> nickcheck(String nickname);
}
