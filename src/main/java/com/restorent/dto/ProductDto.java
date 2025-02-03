package com.restorent.dto;
	
import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
	
@Data
	public class ProductDto implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private Long id;
		private String name;
		private String price;
		private String description;
	    private byte[] returnedImg;
	    private MultipartFile img; 
	    private Long categoryId;
	    private String categoryName;
	    
	    
		
	    public ProductDto(Long id, String name, String price, String description, byte[] returnedImg, MultipartFile img,
				Long categoryId, String categoryName) {
			super();
			this.id = id;
			this.name = name;
			this.price = price;
			this.description = description;
			this.returnedImg = returnedImg;
			this.img = img;
			this.categoryId = categoryId;
			this.categoryName = categoryName;
		}
	
		public ProductDto() {}
	
		
	    
	
	}
