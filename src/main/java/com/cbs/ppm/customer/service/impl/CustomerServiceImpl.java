package com.cbs.ppm.customer.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.cbs.ppm.customer.enums.CustomerType;
import com.cbs.ppm.customer.factory.CustomerFactory;
import com.cbs.ppm.customer.factory.dto.CustomerDTO;
import com.cbs.ppm.customer.model.Customer;
import com.cbs.ppm.customer.repository.CustomerRepository;
import com.cbs.ppm.customer.service.CustomerEventService;
import com.cbs.ppm.customer.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private final Map<CustomerType, CustomerFactory> customerFactoriesByTypes;
	private final CustomerRepository customerRepository;
	private final CustomerEventService customerEventService;

	public CustomerServiceImpl(List<CustomerFactory> customerFactories, CustomerRepository customerRepository,
			CustomerEventService customerEventService) {
		customerFactoriesByTypes = customerFactories.stream()
				.collect(Collectors.toMap(CustomerFactory::getCustomerType, Function.identity()));
		this.customerRepository = customerRepository;
		this.customerEventService = customerEventService;
	}

	@Override
	public List<Customer> getCustomers() {
		return customerRepository.findAll();
	}

	@Override
	public Customer addCustomer(CustomerDTO customerDTO, CustomerType customerType) {
		CustomerFactory customerFactory = customerFactoriesByTypes.get(customerType);
		if (customerFactory == null) {
			throw new IllegalArgumentException("Invalid customer type " + customerType);
		}
		Customer customer = customerFactory.createCustomer(customerDTO);
		
		customerEventService.newCustomerEvent(customer);

		return customerRepository.save(customer);
	}

}
