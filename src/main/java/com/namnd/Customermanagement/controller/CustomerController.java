package com.namnd.Customermanagement.controller;

import com.namnd.Customermanagement.model.Customer;
import com.namnd.Customermanagement.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("/create")
    ResponseEntity<Customer> createCustomer(Customer customer) {
        Customer customerResult = this.customerService.createCustomer(customer);
        return new ResponseEntity<>(customerResult, HttpStatus.OK);
    }

    @PutMapping("/update")
    ResponseEntity<Customer> updateCustomer(Customer customer) {
        Customer customerExist = this.customerService.findCustomerById(customer.getId());

        if (customerExist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Customer customerResult = this.customerService.createCustomer(customer);
        return new ResponseEntity<>(customerResult, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteCustomer(@PathVariable Long id) {
        Customer customerExist = this.customerService.findCustomerById(id);

        if (customerExist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        this.customerService.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/getAll")
    ResponseEntity<  List<Customer>> findAllCustomer() {
        List<Customer> customerResult = this.customerService.findAll();
        return new ResponseEntity<>(customerResult, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    ResponseEntity<Customer> findById(@PathVariable Long id) {
        Customer customerResult = this.customerService.findCustomerById(id);
        return new ResponseEntity<>(customerResult, HttpStatus.OK);
    }

}
