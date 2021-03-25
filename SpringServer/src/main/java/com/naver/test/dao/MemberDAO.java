package com.naver.test.dao;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.naver.test.dto.MemberDTO;

public interface MemberDAO {
	//로그인
	public int login(MemberDTO memberDto);

	//회원 가입
	public Map<String, Object> join(MemberDTO memberDto, HttpServletRequest request);
	
	//이메일 중복체크
	public int emailcheck(String email);

	//이메일 중복체크
	public int nickcheck(String nickname);
}
