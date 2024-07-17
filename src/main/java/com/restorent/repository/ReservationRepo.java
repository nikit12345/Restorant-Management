package com.restorent.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restorent.entity.Reservation;

@Repository
public interface ReservationRepo extends JpaRepository<Reservation, Long>{

}
