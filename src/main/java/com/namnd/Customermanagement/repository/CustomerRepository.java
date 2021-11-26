package com.namnd.Customermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.namnd.Customermanagement.model.Customer;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	Customer findByEmail(String email);

}
