package com.restorent.entity;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.restorent.dto.ReservationDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Reservation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String tableType;
	
	private String description;
	
	private Date date;
	
	private ReservationStatus  reservationStatus;
	 
	private String CustomerName;
	
	private Long customerId;
	

	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;

	
	
	public Reservation(Long id, String tableType, String description, Date date, ReservationStatus reservationStatus,
			String customerName, Long customerId, User user) {
		super();
		this.id = id;
		this.tableType = tableType;
		this.description = description;
		this.date = date;
		this.reservationStatus = reservationStatus;
		CustomerName = customerName;
		this.customerId = customerId;
		this.user = user;
	}

	public ReservationDto getReservationDto() {
		ReservationDto reservationDto = new ReservationDto();
		reservationDto.setTableType(tableType);
		reservationDto.setDate(date);
		reservationDto.setDescription(description);
		reservationDto.setId(id);
		reservationDto.setReservationStatus(reservationStatus);
		reservationDto.setCustomerName(user.getName());
		reservationDto.setCustomerId(user.getId());

		return reservationDto;
	}
	
	public Reservation() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public ReservationStatus getReservationStatus() {
		return reservationStatus;
	}

	public void setReservationStatus(ReservationStatus reservationStatus) {
		this.reservationStatus = reservationStatus;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCustomerName() {
		return CustomerName;
	}

	public void setCustomerName(String customerName) {
		CustomerName = customerName;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	
	
	
	
	
	

}
