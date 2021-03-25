package com.naver.test.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.naver.test.dao.ItemDAO;
import com.naver.test.dto.ItemDTO;
import com.naver.test.util.WriteFile;


@Service
public class ItemServiceImpl implements ItemService {

	@Autowired
	ItemDAO itemDao;

	@Override
	@Transactional
	public void list(HttpServletRequest request) {
		Map<String, Object>map = 
			new HashMap<String, Object>();
		//DAO 메소드를 호출해서 결과를 받습니다.
		List<ItemDTO> list = itemDao.list(map);

		int datacount = itemDao.count(map);
		
		//Controller로 리턴한 데이터를 만들던가 
		//클라이언트에 출력할 데이터를 request에 저장
		Map<String, Object> result = 
				new HashMap<String, Object>();
		result.put("count", datacount);
		result.put("list", list);
		request.setAttribute("result", result);
		
	}

	@Override
	@Transactional
	public ItemDTO detail(int itemid) {
		//파라미터를 읽기
		String id = Integer.toString(itemid);
		//예외 발생 여부를 차단하기 위해서 itemid가 있는 경우만 정수로 변경해서 저장
		if(id != null) {
			itemid = Integer.parseInt(id);
		}
		
		ItemDTO item = null;
		//DAO 메소드 호출해서 결과를 가져온다.
		item = itemDao.detail(itemid);
		return item;
	}

	@Override
	@Transactional
	public Map<String, Object> insert(ItemDTO itemDto, HttpServletRequest request) {
		//ItemId를 생성
		int itemid = 1;
		//데이터 개수 가져오기
		Map<String, Object>map = new HashMap<String, Object>();
		int count = itemDao.count(map);
		//데이터가 존재하는 경우는 가장 itemid + 1
		if(count != 0) {
			itemid = itemDao.maxid() + 1;
			itemDto.setItemid(itemid);
		}

		//파일저장 함수 인스턴스 생성
		WriteFile wf = new WriteFile();
		//웹어플리케이션의 상대경로를 가져온다
		String path = request.getSession().getServletContext().getRealPath("fileUpload");
		MultipartFile file = itemDto.getPictureurlfile();
		
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
			Long size = itemDto.getPictureurlfile().getSize();
			// 서버에서 저장할 파일 이름 (파일 이름이 중복되지 않게 randomUUID 사용)
			String saveFileName = UUID.randomUUID() + extName;
			
			System.out.println("originFilename : " + originFilename);
			System.out.println("extensionName : " + extName);
			System.out.println("size : " + size);
			System.out.println("saveFileName : " + saveFileName);
			
			//파일쓰기 (파일, 파일명, 경로)
			wf.writeFile(itemDto.getPictureurlfile(), saveFileName, path);
			
			itemDto.setPictureurl(saveFileName);
			itemDao.insert(itemDto, request);
			map.put("result", true);
		}
		catch (IOException e) {
			e.getStackTrace();
			map.put("result", false);
		}
		return map;
	}

	@Override
	public Map<String, Object> delete(ItemDTO itemDto, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		int result = itemDao.delete(itemDto, request);
		if (result > 0) {
			map.put("result", true);
		}else {
			map.put("result", false);
		}
		return map;
	}

	@Override
	@Transactional
	public Map<String, Object> update(ItemDTO itemDto, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		
		//파일저장 함수 인스턴스 생성
		WriteFile wf = new WriteFile();
		//웹어플리케이션의 상대경로를 가져온다
		String path = request.getSession().getServletContext().getRealPath("fileUpload");
		MultipartFile file = itemDto.getPictureurlfile();
		
		System.out.println(file.getSize());
		
		String originFilename = null;

		try {
			if (file != null) {
				//파일명 가져오기
				originFilename = file.getOriginalFilename();
			} else {
				//파일이 없다면 default 값으로 저장
				originFilename = "default.jpg";
			}
			if(file.getSize()>0) {
				// 파일명과 확장자명을 분리 (확장자만 저장)
				String extName = originFilename.substring(originFilename.lastIndexOf("."), originFilename.length());
				Long size = itemDto.getPictureurlfile().getSize();
				// 서버에서 저장할 파일 이름 (파일 이름이 중복되지 않게 randomUUID 사용)
				String saveFileName = UUID.randomUUID() + extName;
				
				System.out.println("originFilename : " + originFilename);
				System.out.println("extensionName : " + extName);
				System.out.println("size : " + size);
				System.out.println("saveFileName : " + saveFileName);
				
				//파일쓰기 (파일, 파일명, 경로)
				wf.writeFile(itemDto.getPictureurlfile(), saveFileName, path);
				
				itemDto.setPictureurl(saveFileName);
			} else {
				System.out.println(itemDto.getPictureurl());
				itemDto.setPictureurl(itemDto.getPictureurl());
			}
			itemDao.update(itemDto, request);
			map.put("result", true);
		}
		catch (IOException e) {
			e.getStackTrace();
			map.put("result", false);
		}
		
		return map;
	}

	@Override
	public Map<String, Object> updatetime(HttpServletRequest request) {
		return itemDao.updatetime(request);
	}

}
