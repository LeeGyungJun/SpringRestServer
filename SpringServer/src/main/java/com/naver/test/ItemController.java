package com.naver.test;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.naver.test.dto.ItemDTO;
import com.naver.test.service.ItemService;

@RestController
@RequestMapping(value = "/item")
public class ItemController {
	@Autowired
	ItemService itemService;

	//목록 보기
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public Map<String, Object> list(HttpServletRequest request){
		itemService.list(request);
		Map<String, Object> map = (Map<String, Object>)request.getAttribute("result");
		System.out.println("item list");
		return map;
	}

	//상세 보기
	@RequestMapping(value="/detail", method=RequestMethod.GET)
	public ItemDTO detail(ItemDTO itemDto){
		System.out.println("item detail");
		return itemService.detail(itemDto.getItemid());
	}

	//아이템 삽입
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public Map<String, Object> insert(ItemDTO itemDto, HttpServletRequest request){
		System.out.println("item insert");
		return itemService.insert(itemDto, request);
	}

	//아이템 삭제
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public Map<String, Object> delete(ItemDTO itemDto, HttpServletRequest request){
		System.out.println("item delete");
		return itemService.delete(itemDto, request);
	}
	
	//아이템 수정
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public Map<String, Object> update(ItemDTO itemDto, HttpServletRequest request){
		System.out.println("item update");
		return itemService.update(itemDto, request);
	}
	
	//아이템 수정 뷰 (REST)
	@RequestMapping(value = "/update-view/{itemid}", method = RequestMethod.GET)
	public ItemDTO updateView(@PathVariable int itemid) {
		System.out.println("item update-view");
		return itemService.detail(itemid);
	}
	
	//데이터 최신 시간
	@RequestMapping(value = "/updatetime", method = RequestMethod.GET)
	public Map<String, Object> updatetime(HttpServletRequest request) {
		System.out.println("item updatetime");
		return itemService.updatetime(request);
	}
}
