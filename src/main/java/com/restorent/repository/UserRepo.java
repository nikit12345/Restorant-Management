package com.restorent.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restorent.entity.User;
import com.restorent.enums.UserRole;

@Repository
public interface UserRepo extends JpaRepository<User, Long>{
	
	public Optional<User> findFirstByEmail(String email);

	public User findByUserRole(UserRole admin);
	

}
