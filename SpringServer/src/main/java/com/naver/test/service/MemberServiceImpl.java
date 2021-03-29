package com.naver.test.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.naver.test.dao.MemberDAO;
import com.naver.test.dto.MemberDTO;
import com.naver.test.util.Encrypt;
import com.naver.test.util.WriteFile;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	MemberDAO memberDao;
	

	@Override
	public Map<String, Object> login(MemberDTO memberDto) {
		Map<String, Object> map = new HashMap<String, Object>();
		//암호화 함수 인스턴스 생성
		Encrypt enc = new Encrypt();
		//암호화
		String pw = enc.encryptSHA256(memberDto.getPw());
		memberDto.setPw(pw);

		int rs = memberDao.login(memberDto);
		if (rs > 0) {
			map.put("result", true);
			map.put("msg", "로그인 성공");
		} else {
			map.put("result", false);
			map.put("msg", "로그인 실패");
		}
		
		return map;
	}
	
	//회원가입
	@Override
	public Map<String, Object> join(MemberDTO memberDto, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		//암호화 함수 인스턴스 생성
		Encrypt enc = new Encrypt();
		//파일저장 함수 인스턴스 생성
		WriteFile wf = new WriteFile();
		//웹어플리케이션의 상대경로를 가져온다
		String path = request.getSession().getServletContext().getRealPath("fileUpload");
		MultipartFile file = memberDto.getProfile();
		
		System.out.println(path);
		String originFilename = null;
		
		try {
			if (file != null) {
				//파일명 가져오기
				originFilename = file.getOriginalFilename();
			} else {
				//파일이 없다면 default 값으로 저장
				originFilename = "default.jpg";
			}
			// 파일명과 확장자명을 분리 (확장자만 저장)
			String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
			Long size = memberDto.getProfile().getSize();
			// 서버에서 저장할 파일 이름 (파일 이름이 중복되지 않게 randomUUID 사용)
			String saveFileName = UUID.randomUUID() + extName;
			
			System.out.println("originFilename : " + originFilename);
			System.out.println("extensionName : " + extName);
			System.out.println("size : " + size);
			System.out.println("saveFileName : " + saveFileName);
			
			//파일쓰기 (파일, 파일명, 경로)
			wf.writeFile(memberDto.getProfile(), saveFileName, path);
			
			//암호화
			String pw = enc.encryptSHA256(memberDto.getPw());
			memberDto.setPw(pw);
			memberDto.setProfileName(saveFileName);
			memberDao.join(memberDto, request);
			map.put("result", true);
		}
		catch (IOException e) {
			e.getStackTrace();
			map.put("result", false);
		}
		return map;
	}
	

	//이메일 중복체크
	@Override
	public Map<String, Object> emailcheck(String email) {
		Map<String, Object> map = new HashMap<String, Object>();
		int rs = memberDao.emailcheck(email);
		if (rs > 0) {
			map.put("result", false);
			map.put("msg", "사용불가한 이메일");
		} else {
			map.put("result", true);
			map.put("msg", "사용가능한 이메일");
		}
		return map;
	}
	

	//이메일 중복체크
	@Override
	public Map<String, Object> nickcheck(String nickname) {
		Map<String, Object> map = new HashMap<String, Object>();
		int rs = memberDao.nickcheck(nickname);
		if (rs > 0) {
			map.put("result", false);
			map.put("msg", "사용불가한 닉네임");
		} else {
			map.put("result", true);
			map.put("msg", "사용가능한 닉네임");
		}
		return map;
	}


}
