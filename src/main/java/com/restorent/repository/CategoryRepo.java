package com.restorent.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restorent.dto.CategoryDto;
import com.restorent.entity.Category;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Long>{

	List<Category> findAllByNameContaining(String title);


}
