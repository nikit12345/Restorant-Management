package com.restorent.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.restorent.dto.CategoryDto;
import com.restorent.dto.ProductDto;
import com.restorent.dto.ReservationDto;
import com.restorent.services.CustomerService;

@RestController
@RequestMapping("api/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
    @CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryDto>> getAllCategory(){
		List<CategoryDto> categoryDto = customerService.getAllCategory();
		if(categoryDto== null) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(categoryDto);
	}
	
   @GetMapping("getByName/{title}")
	public ResponseEntity<List<CategoryDto>> getAllCategoryByName(@PathVariable String title){
		List<CategoryDto> categoryDto = customerService.getCategoryByName(title);
		if(categoryDto== null) return ResponseEntity.noContent().build();
		return ResponseEntity.ok(categoryDto);
	}
   
   @GetMapping("{categoryId}/productsById")//get products by category
   @CrossOrigin(origins = "http://localhost:4200")

	public ResponseEntity<List< ProductDto>> getProductByCategoryId(@PathVariable Long categoryId){
		List<ProductDto> prodDtos = customerService.getAllProdByCategory(categoryId);
		if(prodDtos==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(prodDtos);
	}
   
   @GetMapping("{categoryId}/product/{title}")
   @CrossOrigin(origins = "http://localhost:4200")

	public ResponseEntity<List< ProductDto>> getProductByCategoryAndTitle(@PathVariable Long categoryId ,@PathVariable String title){
		List<ProductDto> prodDtos = customerService.getProductByCategoryAndTitle(categoryId, title);
		if(prodDtos==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(prodDtos);
	}

	@PostMapping("/reservation")
	public ResponseEntity<?> postReservation(@RequestBody ReservationDto reservationDto){
		ReservationDto reservationDto2 = customerService.postReservation(reservationDto);
		if(reservationDto2 == null) return new ResponseEntity<>("something went wrong", HttpStatus.BAD_REQUEST);
		return ResponseEntity.status(HttpStatus.CREATED).body(reservationDto2);
	}
	
	

}
