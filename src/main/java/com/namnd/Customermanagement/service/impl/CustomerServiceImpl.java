package com.namnd.Customermanagement.service.impl;

import com.namnd.Customermanagement.model.Customer;
import com.namnd.Customermanagement.repository.CustomerRepository;
import com.namnd.Customermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {

        if(customer.getId() != null){
            return customerRepository.save(customer);
        }

        return null;
    }

    @Override
    public Customer findCustomerByEmail(String email) {
        return this.customerRepository.findByEmail(email);
    }

    @Override
    public Customer findCustomerById(Long id) {
        Optional<Customer> customerResult = this.customerRepository.findById(id);
        return customerResult.orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        Optional<Customer> customerResult = this.customerRepository.findById(id);
        if(customerResult.isPresent()){
            this.customerRepository.deleteById(id);
        }
    }

	@Override
	public List<Customer> findAll() {
		return this.customerRepository.findAll();
	}
}
