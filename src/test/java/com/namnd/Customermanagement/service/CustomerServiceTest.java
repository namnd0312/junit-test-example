package com.namnd.Customermanagement.service;

import com.namnd.Customermanagement.dto.CustomerDto;
import com.namnd.Customermanagement.model.Customer;
import org.junit.Ignore;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;

/**
 * @author nam.nd
 * @created 26/11/2021 - 3:13 PM
 */

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Ignore
class CustomerServiceTest {

	@Autowired
	private CustomerService customerService;

	@Order(1)
	@Test
	@Rollback(value = false)
	void testCreateCustomer() {
		CustomerDto customer = new CustomerDto();
		customer.setName("Nghiem Duc Nam");
		customer.setAge(25);
		customer.setEmail("namnd0312@gmail.com");
		customer.setMaritalStatus(false);
		customer.setAddress("Dong Anh - Ha Noi");

		Customer customerEntity = this.customerService.createCustomer(customer);

		assertNotNull(customerEntity);
	}

	@Order(2)
	@Test
	void findCustomerByEmailExist() {
		String emailCustomer = "namnd0312@gmail.com";

		Customer customer = this.customerService.findCustomerByEmail(emailCustomer);

		assertThat(customer.getEmail()).isEqualTo(emailCustomer);

	}

	@Order(3)
	@Test
	void findCustomerByEmailNotExist() {
		String emailCustomer = "namnd0312@gmail.com3";

		Customer customer = this.customerService.findCustomerByEmail(emailCustomer);

		assertNotNull(customer);
	}

	@Order(4)
	@Test
	@Rollback(value = false)
	void testUpdateCustomer() {
		CustomerDto cus = customerService.findCustomerById(1L);

		assertNotNull(cus);

		cus.setEmail("nam@gmail.com");
		cus.setAddress("TP Hồ Chí Minh");
		cus.setAge(50);
		cus.setMaritalStatus(true);
		cus.setName("Hoang van A");

		Customer customerUpdated = customerService.updateCustomer(cus);

		Assertions.assertThat(customerUpdated.getEmail()).isEqualTo("nam@gmail.com");
		Assertions.assertThat(customerUpdated.getAddress()).isEqualTo("TP Hồ Chí Minh");
		Assertions.assertThat(customerUpdated.getAge()).isEqualTo(50);
		Assertions.assertThat(customerUpdated.getMaritalStatus()).isTrue();
		Assertions.assertThat(customerUpdated.getName()).isEqualTo("Hoang van A");
	}

	@Order(5)
	@Test
	void findCustomerById() {
		Long id = 1L;

        Customer customer = this.customerService.findCustomerById(id);

    	assertNotNull(customer);
	}

	@Test
	@Order(6)
	@Rollback(value = false)
	void deleteCustomerByIdTest() {

//        Kiểm tra customer có tồn tại trong DB trước khi xóa
		Customer customer = customerService.findCustomerById(1L);
		assertNotNull(customer);

		customerService.deleteById(customer.getId());

//        Kiểm tra customer đã được xóa hay chưa

		Customer customerActual = customerService.findCustomerById(customer.getId());

		Assertions.assertThat(customerActual).isNull();
	}
	
	@Test
	@Order(7)
	public void getListOfCustomerTest() {

		List<Customer> customers = customerService.findAll();

		Assertions.assertThat(customers.size()).isGreaterThan(0);

	}
}
