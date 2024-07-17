package com.restorent.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restorent.dto.CategoryDto;
import com.restorent.dto.ProductDto;
import com.restorent.dto.ReservationDto;
import com.restorent.entity.Category;
import com.restorent.entity.Product;
import com.restorent.entity.Reservation;
import com.restorent.entity.ReservationStatus;
import com.restorent.entity.User;
import com.restorent.repository.CategoryRepo;
import com.restorent.repository.ProductRepo;
import com.restorent.repository.ReservationRepo;
import com.restorent.repository.UserRepo;

import io.jsonwebtoken.lang.Objects;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private ReservationRepo reservationRepo;
	
	@Autowired
	private UserRepo userRepo;
	
	@Override
	public List<CategoryDto> getAllCategory() {
		
		return categoryRepo.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());

	}

	@Override
	public List<CategoryDto> getCategoryByName(String title) {
		
		
		return categoryRepo.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
	}
	
	@Override
	public List<ProductDto> getAllProdByCategory(Long categoryId) {
		
		return productRepo.findAllProductsByCategoryId(categoryId).stream().map(Product
				::getProductDto).collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getProductByCategoryAndTitle(Long categoryId, String title) {
		// TODO Auto-generated method stub
		return productRepo.findAllByCategoryIdAndNameContaining(categoryId, title).stream().map
				(Product::getProductDto).collect(Collectors.toList());
	}

	@Override
	public ReservationDto postReservation(ReservationDto reservationDto) {
        Optional<User> optional = userRepo.findById(reservationDto.getCustomerId());
        if(optional.isPresent()) {
        	Reservation reservation = new Reservation();
        	reservation.setTableType(reservationDto.getTableType());
        	reservation.setDescription(reservationDto.getDescription());
            reservation.setDate(reservationDto.getDate());
            reservation.setUser(optional.get());
            reservation.setReservationStatus(ReservationStatus.PENDING);
            
            Reservation postedReservatopn = reservationRepo.save(reservation);
            
            ReservationDto postedrReservationDto = new ReservationDto();
            postedrReservationDto.setId(postedrReservationDto.getId());
            return(postedrReservationDto);
        }
		return null;
	}

	

	

}
