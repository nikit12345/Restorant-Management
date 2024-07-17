package com.restorent.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restorent.entity.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long>{


	List<Product> findAllByCategoryIdAndNameContaining(Long categoryId, String title);

	List<Product> findAllProductsByCategoryId(Long categoryId);

}
	