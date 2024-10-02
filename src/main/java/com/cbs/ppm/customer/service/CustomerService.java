package com.cbs.ppm.customer.service;

import java.util.List;

import com.cbs.ppm.customer.enums.CustomerType;
import com.cbs.ppm.customer.factory.dto.CustomerDTO;
import com.cbs.ppm.customer.model.Customer;

public interface CustomerService {

	public Customer addCustomer(CustomerDTO customerDTO, CustomerType customerType);
	
	public List<Customer> getCustomers();
}
