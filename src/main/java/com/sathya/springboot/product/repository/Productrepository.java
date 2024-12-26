package com.sathya.springboot.product.repository;






import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sathya.springboot.product.entity.Productentity;

@Repository
public interface Productrepository extends JpaRepository<Productentity, Long>{

	

	
	}
	
	


