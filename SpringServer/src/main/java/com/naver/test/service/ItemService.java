package com.naver.test.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.naver.test.dto.ItemDTO;


public interface ItemService {

	//목록 보기
	public void list(HttpServletRequest request);

	//상세 보기
	public ItemDTO detail(int itemid);
	
	//상품 추가
	public Map<String, Object> insert(ItemDTO itemDto, HttpServletRequest request);

	//상품 삭제
	public Map<String, Object> delete(ItemDTO itemDto, HttpServletRequest request);

	//상품 수정
	public Map<String, Object> update(ItemDTO itemDto, HttpServletRequest request);

	//상품 수정
	public Map<String, Object> updatetime(HttpServletRequest request);

}
