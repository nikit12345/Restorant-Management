package com.restorent.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.restorent.dto.CategoryDto;
import com.restorent.dto.ProductDto;
import com.restorent.dto.ReservationDto;
import com.restorent.entity.Product;

public interface AdminService {
	
	

	CategoryDto postCategory(CategoryDto categoryDto) throws IOException;

	List<CategoryDto> getAllCategory();

	List<CategoryDto> getAllCategoryByTitle(String title);

	ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException;

	List<ProductDto> getAllProdByCategory(Long categoryId);

	List<ProductDto> getProductByCategoryAndTitle(Long categoryId, String title);

	void deleteProdById(Long prodId);

	ProductDto updateProduct(Long prodId, ProductDto productDto) throws IOException;

	ProductDto findProdById(Long prodId);

	List<ReservationDto> getReservations();

	ReservationDto changeReservationStatus(Long reservationId, String status);

}
