package com.cbs.ppm.customer.factory;

import com.cbs.ppm.customer.enums.CustomerType;
import com.cbs.ppm.customer.factory.dto.CustomerDTO;
import com.cbs.ppm.customer.model.Customer;

public interface CustomerFactory {

	public Customer createCustomer(CustomerDTO customerDTO);
	public CustomerType getCustomerType();
}
