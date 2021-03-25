package com.naver.test.dao;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.test.dto.MemberDTO;

@Repository
public class MemberDAOImpl implements MemberDAO {
	
	@Autowired
	private SqlSession sqlSession;

	//로그인
	@Override
	public int login(MemberDTO memberDto) {
		return sqlSession.selectOne("dbMember.login", memberDto);
	}
	
	//회원 가입
	@Override
	public Map<String, Object> join(MemberDTO memberDto, HttpServletRequest request) {
		int result = sqlSession.insert("dbMember.getJoin", memberDto);
		Map<String, Object>map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}

	//이메일 중복체크
	@Override
	public int emailcheck(String email) {
		return sqlSession.selectOne("dbMember.emailcheck", email);
	}
	
	//닉네임 중복체크
	@Override
	public int nickcheck(String nickname) {
		return sqlSession.selectOne("dbMember.nickcheck", nickname);
	}

}
