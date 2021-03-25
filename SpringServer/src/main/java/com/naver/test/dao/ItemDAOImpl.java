package com.naver.test.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.naver.test.dto.ItemDTO;
@Repository
public class ItemDAOImpl implements ItemDAO {

	@Autowired
	private SqlSession sqlSession;

	@Override
	public List<ItemDTO> list(Map<String, Object> map) {
		List<ItemDTO> result = new ArrayList<ItemDTO>();
		result = sqlSession.selectList("dbItem.itemlist");
		return result;
	}
	
	@Override
	public int count(Map<String, Object> map) {
		int result = 0;
		result = sqlSession.selectOne("dbItem.count");
		return result;
	}

	//테이블에서 데이터 1개를 가져오는 메소드
	@Override
	public ItemDTO detail(int itemid) {
		ItemDTO item = null;
		//기본키를 가지고 하나의 데이터 찾아오는 방법
		item = sqlSession.selectOne("dbItem.detail", itemid);
		return item;
	}

	@Override
	public Map<String, Object> insert(ItemDTO itemDto, HttpServletRequest request) {
		int result = sqlSession.insert("dbItem.insert", itemDto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		updatedate(request);
		return map;
	}
	
	@Override
	public int maxid() {
		int result = 0;
		result = sqlSession.selectOne("dbItem.maxid");
		return result;
	}

	@Override
	public int delete(ItemDTO itemDto, HttpServletRequest request) {
		updatedate(request);
		return sqlSession.delete("dbItem.delete", itemDto);
	}

	@Override
	public Map<String, Object> update(ItemDTO itemDto, HttpServletRequest request) {
		int result = sqlSession.update("dbItem.update", itemDto);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		updatedate(request);
		return map;
	}

	@Override
	public void updatedate(HttpServletRequest request) {
		try {
			String path = request.getSession().getServletContext().getRealPath("resources/updatetime.txt");
			FileWriter fw = new FileWriter(path);
			BufferedWriter bw = new BufferedWriter(fw);
			Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String today = sdf.format(date);
	        System.out.println(today);
			bw.write(today);
			bw.flush();
			bw.close();
		}catch(Exception e) {
			e.getStackTrace();
		}
	}

	@Override
	public Map<String, Object> updatetime(HttpServletRequest request) {
		Map<String, Object> result = new HashMap<String, Object>();
		String path = request.getSession().getServletContext().getRealPath("resources/updatetime.txt");
		try {
			File file = new File(path);
			BufferedReader br = new BufferedReader(new FileReader(file));
		    String line = br.readLine();
		    result.put("result", line);
		} catch (IOException e) {
		    e.printStackTrace();
		    result.put("result", false);
		}
		return result;
	}


}
