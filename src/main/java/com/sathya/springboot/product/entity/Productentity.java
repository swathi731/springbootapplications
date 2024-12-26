package com.sathya.springboot.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor


public class Productentity {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 

	private Long id;
	private String name;
	private double price;
	private String brand;
	private String madein;
	private int quantity;
	private int discountrate;
	private double taxrate;
	private double discountprice;
	private double offerprice;
	private double finalprice;
	private double stockvalue;
	
	

}
