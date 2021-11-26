package com.namnd.Customermanagement.service;

import com.namnd.Customermanagement.model.Customer;

public interface CustomerService {

    Customer createCustomer(Customer customer);

    Customer updateCustomer(Customer customer);

    Customer findCustomerByEmail(String email);

    Customer findCustomerById(Long id);

    void deleteById(Long id);
    
    java.util.List<Customer> findAll();

}
