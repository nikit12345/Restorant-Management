package com.restorent.services;

import java.util.List;
import java.util.Optional;

import com.restorent.dto.CategoryDto;
import com.restorent.dto.ProductDto;
import com.restorent.dto.ReservationDto;
import com.restorent.entity.Category;

public interface CustomerService {

	public List<CategoryDto> getAllCategory();

	public List<CategoryDto> getCategoryByName(String title);

	List<ProductDto> getAllProdByCategoryId(Long categoryId);

	public List<ProductDto> getProductByCategoryAndTitle(Long categoryId, String title);

	public ReservationDto postReservation(ReservationDto reservationDto);
	
	public Optional<Category> getByCategoryId(Long categoryId);

	
}
