package com.namnd.Customermanagement.service.impl;

import com.namnd.Customermanagement.model.Customer;
import com.namnd.Customermanagement.service.CustomerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Override
    public Customer createCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return null;
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return null;
    }

    @Override
    public Customer findCustomerById(Long id) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }

	@Override
	public List<Customer> findAll() {
		// TODO Auto-generated method stub
		return null;
	}
}
