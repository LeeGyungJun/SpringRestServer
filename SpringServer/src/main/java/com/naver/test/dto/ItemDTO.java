package com.naver.test.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;


@Data
public class ItemDTO {
	
    private int itemid;
    private String itemname;
    private int price;
    private String description;
    private String pictureurl;
    private MultipartFile pictureurlfile;
    private String updatedate;
    
}
