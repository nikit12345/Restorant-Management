package com.restorent.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.restorent.dto.CategoryDto;
import com.restorent.dto.ProductDto;
import com.restorent.dto.ReservationDto;
import com.restorent.entity.Category;
import com.restorent.entity.Product;
import com.restorent.entity.Reservation;
import com.restorent.entity.ReservationStatus;
import com.restorent.repository.CategoryRepo;
import com.restorent.repository.ProductRepo;
import com.restorent.repository.ReservationRepo;

import jakarta.transaction.Transactional;

@Service
public class AdminServiceImpl  implements AdminService{
	
	@Autowired
	private CategoryRepo categoryRepo;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ReservationRepo reservationRepo;


    @Transactional
    public CategoryDto postCategory(CategoryDto categoryDto) throws IOException {
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setImg(categoryDto.getImg().getBytes()); // Convert MultipartFile to byte[]

        Category createdCategory = categoryRepo.save(category);

        CategoryDto createdCategoryDto = new CategoryDto();
        createdCategoryDto.setId(createdCategory.getId());
        createdCategoryDto.setName(createdCategory.getName());
        createdCategoryDto.setDescription(createdCategory.getDescription());
        createdCategoryDto.setReturnedImg(createdCategory.getImg()); // Set the image byte array

        return createdCategoryDto;
    }

	@Override
	public List<CategoryDto> getAllCategory() {
	
		return categoryRepo.findAll().stream().map(Category::getCategoryDto).collect(Collectors.toList());
	}

	@Override
	public List<CategoryDto> getAllCategoryByTitle(String title) {
		
		return categoryRepo.findAllByNameContaining(title).stream().map(Category::getCategoryDto).collect(Collectors.toList());
	}
	
	// product operations

	@Transactional
	public ProductDto postProduct(Long categoryId, ProductDto productDto) throws IOException {
	    Optional<Category> optionalCategory = categoryRepo.findById(categoryId);
	    if (optionalCategory.isPresent()) {
	        Product product = new Product();
	        BeanUtils.copyProperties(productDto, product);
	        
	        if (productDto.getImg() != null) {
	            product.setImg(productDto.getImg().getBytes());
	        }
	        product.setCategory(optionalCategory.get());

	        Product createdProduct = productRepo.save(product);
	        return createdProduct.getProductDto();
	    }
	    return null;
	}


	@Override
	public List<ProductDto> getAllProdByCategory(Long categoryId) {
		
		return productRepo.findAllProductsByCategoryId(categoryId).stream().map(Product
				::getProductDto).collect(Collectors.toList());
	}

	@Override
	public List<ProductDto> getProductByCategoryAndTitle(Long categoryId,String title) {
		// TODO Auto-generated method stub
		return productRepo.findAllByCategoryIdAndNameContaining(categoryId, title).stream().map(Product
				::getProductDto).collect(Collectors.toList());	}

	@Override
	public void deleteProdById(Long prodId) {
		 Optional<Product> optional = productRepo.findById(prodId);
		 if(optional.isPresent()) {
			 productRepo.deleteById(prodId);
		 }
		 throw new IllegalArgumentException("product with id "+prodId+" is not found");
		 
	}

	@Override
	public ProductDto updateProduct(Long prodId, ProductDto productDto) throws IOException {
        Optional<Product> optionalProduct = productRepo.findById(prodId);
        if (!optionalProduct.isPresent()) {
            throw new IllegalArgumentException("Product with id " + prodId + " not found");
        }

        Product product = optionalProduct.get();
        BeanUtils.copyProperties(productDto, product, "id", "img");

        // Update the image if provided
        if (productDto.getImg() != null && !productDto.getImg().isEmpty()) {
            product.setImg(productDto.getImg().getBytes());
        }

        productRepo.save(product);

        return product.getProductDto();
    }

	@Override
	public ProductDto findProdById(Long prodId) {
		Optional<Product> pOptional = productRepo.findById(prodId);
		if(pOptional.isPresent()) {
			return pOptional.map(Product::getProductDto).orElse(null);
		}else throw new IllegalArgumentException("id not found");
	}

	
	@Override
	public List<ReservationDto> getReservations() {
		
		return  reservationRepo.findAll().stream().map(Reservation::getReservationDto).collect(Collectors.toList());
	}
	
	@Override
	public ReservationDto changeReservationStatus(Long reservationId, String status) {
		Optional<Reservation> optional = reservationRepo.findById(reservationId);
		if(optional.isPresent())
		{
			Reservation existingReservation = optional.get();
			if(status.equals("APPROVE")) {
				existingReservation.setReservationStatus(ReservationStatus.APPROVED);
			}else {
				existingReservation.setReservationStatus(ReservationStatus.DISAPPROVED);
			}
			Reservation updaReservation = reservationRepo.save(existingReservation);
			ReservationDto updatedReservationDto = new ReservationDto();
			updatedReservationDto.setId(updaReservation.getId());
			return updatedReservationDto;
		}
		return null;
	}

	

}
