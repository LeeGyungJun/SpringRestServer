package com.naver.test.dto;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class MemberDTO {
	
	private String email;
	private String nickname;
	private String pw;
	private String profileName;
	private MultipartFile profile;
	private Date regdate;
	private Date logindate;

}
