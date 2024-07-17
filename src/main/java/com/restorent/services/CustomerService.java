package com.restorent.services;

import java.util.List;

import com.restorent.dto.CategoryDto;
import com.restorent.dto.ProductDto;
import com.restorent.dto.ReservationDto;

public interface CustomerService {

	public List<CategoryDto> getAllCategory();

	public List<CategoryDto> getCategoryByName(String title);

	List<ProductDto> getAllProdByCategory(Long categoryId);

	public List<ProductDto> getProductByCategoryAndTitle(Long categoryId, String title);

	public ReservationDto postReservation(ReservationDto reservationDto);

	
}
