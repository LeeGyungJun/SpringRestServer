package com.naver.test.util;

import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public class WriteFile {
	

	// 파일을 실제로 write 하는 메서드
	public void writeFile(MultipartFile multipartFile, String saveFileName, String path) throws IOException{
		byte[] data = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream(path + "/" + saveFileName);
		fos.write(data);
		fos.close();
	}

}
