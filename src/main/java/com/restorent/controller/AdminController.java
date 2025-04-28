package com.restorent.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.restorent.dto.CategoryDto;
import com.restorent.dto.ProductDto;
import com.restorent.dto.ReservationDto;
import com.restorent.entity.Product;
import com.restorent.services.AdminService;

@RestController
@RequestMapping()
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://13.201.59.6:6063")

public class AdminController {
	
	@Autowired
	
	private AdminService adminService;
		
	@PostMapping("category")
    public ResponseEntity<CategoryDto> postCategory(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("img") MultipartFile img) throws IOException {

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(name);
        categoryDto.setDescription(description);
        categoryDto.setImg(img); // Set the MultipartFile directly, will handle it in service

        CategoryDto createdCategoryDto = adminService.postCategory(categoryDto);
        if (createdCategoryDto == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.OK);
    }
	
	@GetMapping("getAllCategory")
    @CrossOrigin(origins = "http://localhost:4200")

	public ResponseEntity<List< CategoryDto>> getAllCategory(){
		List<CategoryDto> categoryDtos = adminService.getAllCategory();
		if(categoryDtos==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(categoryDtos);
	}
	
	@GetMapping("getAllCategoryBytitle/{title}")
    @CrossOrigin(origins = "http://localhost:4200")

	public ResponseEntity<List< CategoryDto>> getAllCategoryByTitle(@PathVariable String title){
		List<CategoryDto> categoryDtos = adminService.getAllCategoryByTitle(title);
		if(categoryDtos==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(categoryDtos);
	}
	
	//product operations
	

    
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("{categoryId}/product")
    public ResponseEntity<ProductDto> postProduct(
            @PathVariable Long categoryId,
            @RequestParam("name") String name,
            @RequestParam("price") String price,
            @RequestParam("description") String description,
            @RequestParam("img") MultipartFile img) throws IOException {

        ProductDto productDto = new ProductDto();
        productDto.setName(name);
        productDto.setPrice(price);
        productDto.setDescription(description);
        productDto.setImg(img);

        ProductDto createdProduct = adminService.postProduct(categoryId, productDto);
        if (createdProduct != null) {
            return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping("{categoryId}/productsById")
    @CrossOrigin(origins = "http://localhost:4200")

	public ResponseEntity<List< ProductDto>> getAllProductsByCategory(@PathVariable Long categoryId){
		List<ProductDto> prodDtos = adminService.getAllProdByCategory(categoryId);
		if(prodDtos==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(prodDtos);
	}
    
    @GetMapping("{categoryId}/product/{title}")
    @CrossOrigin(origins = "http://localhost:4200")

	public ResponseEntity<List< ProductDto>> getProductByCategoryAndTitle(@PathVariable Long categoryId ,@PathVariable String title){
		List<ProductDto> prodDtos = adminService.getProductByCategoryAndTitle(categoryId, title);
		if(prodDtos==null) return ResponseEntity.notFound().build();
		return ResponseEntity.ok(prodDtos);
	}

    @DeleteMapping("deleteProd/{prodId}")
    @CrossOrigin(origins = "http://localhost:4200")

    public ResponseEntity<String> deleteProduct(@PathVariable Long prodId){
    	adminService.deleteProdById(prodId);
    	return ResponseEntity.noContent().build();
    }
    
    @PutMapping("updateProd/{prodId}")
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> updateProduct(@PathVariable Long prodId, @ModelAttribute ProductDto productDto) throws IOException {
        ProductDto productDto2 = adminService.updateProduct(prodId, productDto);
        if (productDto2 == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");

        return ResponseEntity.status(HttpStatus.CREATED).body("Products are updated successfully");
    }

    
	    @GetMapping("getProduct/{prodId}")
	    @CrossOrigin(origins = "http://localhost:4200")

	    public ResponseEntity<ProductDto> getProdById(@PathVariable Long prodId){
	    	ProductDto productDto = adminService.findProdById(prodId);
	    	if(productDto==null) return ResponseEntity.notFound().build();
	    	return ResponseEntity.ok(productDto);
	     
	    
	    }
	    
	    @GetMapping("/reservation")
		public ResponseEntity<List<ReservationDto>> getReservations(){
			List<ReservationDto> liDtos = adminService.getReservations();
			if(liDtos == null) return  ResponseEntity.notFound().build();
			return ResponseEntity.ok(liDtos);
		}
		
		@GetMapping("/reservation/{reservationId}/{status}")
		public ResponseEntity<ReservationDto> changeReservationStatus(@PathVariable Long reservationId ,@PathVariable String status){
		     ReservationDto reservationDto = adminService.changeReservationStatus(reservationId, status);
		     if(reservationDto == null) return ResponseEntity.noContent().build();
		     return ResponseEntity.ok(reservationDto);
		}
		
}

