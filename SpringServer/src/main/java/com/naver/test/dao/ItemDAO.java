package com.naver.test.dao;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.naver.test.dto.ItemDTO;


public interface ItemDAO {

	//테이블에서 데이터 목록을 가져오는 메소드
	public List<ItemDTO> list(Map<String, Object> map);
	
	//테이블에서 데이터 총 개수를 가져오는 메소드
	public int count(Map<String, Object> map);

	//테이블에서 데이터 1개를 가져오는 메소드
	public ItemDTO detail(int itemid);
	
	//상품 삽입 메소드
	public Map<String, Object> insert(ItemDTO itemDto, HttpServletRequest request);
	
	//최대 itemid를 구하는 메소드
	public int maxid();

	//상품 삭제 메소드
	public int delete(ItemDTO itemDto, HttpServletRequest request);
	
	//상품 수정 메소드
	public Map<String, Object> update(ItemDTO itemDto, HttpServletRequest request);
	
	//상품 업데이트정보 최신화
	public void updatedate(HttpServletRequest request);

	//업데이트정보 가져오기
	public Map<String, Object> updatetime(HttpServletRequest request);
}
