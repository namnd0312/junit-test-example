package com.namnd.Customermanagement.service;

import com.namnd.Customermanagement.dto.CustomerDto;
import com.namnd.Customermanagement.model.Customer;

import antlr.collections.List;

public interface CustomerService {

    Customer createCustomer(CustomerDto customerDto);

    Customer updateCustomer(CustomerDto customerDto);

    Customer findCustomerByEmail(String email);

    Customer findCustomerById(Long id);

    void deleteById(Long id);
    
    java.util.List<Customer> findAll();

}
