package com.namnd.Customermanagement.repository;

import com.namnd.Customermanagement.model.Customer;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CustomerRepositoryTest {

	@Autowired
	private CustomerRepository customerRepository;

	@Order(1)
	@Test
	@Rollback(value = false)
	void testCreateCustomer() {
		Customer customer = new Customer();
		customer.setName("Nghiem Duc Nam");
		customer.setAge(25);
		customer.setEmail("namnd0312@gmail.com");
		customer.setMaritalStatus(false);
		customer.setAddress("Dong Anh - Ha Noi");

		Customer customerEntity = this.customerRepository.save(customer);

		assertNotNull(customerEntity);
	}

	@Order(2)
	@Test
	void findCustomerByEmailExist() {
		String emailCustomer = "namnd0312@gmail.com";

		Customer customer = this.customerRepository.findByEmail(emailCustomer);

		assertThat(customer.getEmail()).isEqualTo(emailCustomer);

	}

	@Order(3)
	@Test
	void findCustomerByEmailNotExist() {
		String emailCustomer = "namnd0312@gmail.com";

		Customer customer = this.customerRepository.findByEmail(emailCustomer);

		assertNotNull(customer);
	}

	@Order(4)
	@Test
	@Rollback(value = false)
	void testUpdateCustomer() {
		Customer cus = customerRepository.findById(1L).get();

		assertNotNull(cus);

		cus.setEmail("nam@gmail.com");
		cus.setAddress("TP Hồ Chí Minh");
		cus.setAge(50);
		cus.setMaritalStatus(true);
		cus.setName("Hoang van A");

		Customer customerUpdated = customerRepository.save(cus);

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
		Customer customer = this.customerRepository.findById(id).get();

		assertNotNull(customer);
	}

	@Test
	@Order(6)
	@Rollback(value = false)
	void deleteCustomerByIdTest() {

		customerRepository.deleteById(1L);

//        Kiểm tra customer đã được xóa hay chưa
		Customer customerActual = null;
		Optional<Customer> customerOptional = customerRepository.findById(1L);

		if (customerOptional.isPresent()) {
			customerActual = customerOptional.get();
		}

		Assertions.assertThat(customerActual).isNull();
	}

	@Test
	@Order(7)
	void getListOfCustomerTest() {

		List<Customer> customers = customerRepository.findAll();

		Assertions.assertThat(customers.size()).isGreaterThan(0);

	}
}
