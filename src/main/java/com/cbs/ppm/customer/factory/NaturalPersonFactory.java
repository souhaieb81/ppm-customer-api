package com.cbs.ppm.customer.factory;

import org.springframework.stereotype.Component;

import com.cbs.ppm.customer.enums.CustomerType;
import com.cbs.ppm.customer.factory.dto.CustomerDTO;
import com.cbs.ppm.customer.model.Customer;
import com.cbs.ppm.customer.model.NaturalPerson;

@Component
public class NaturalPersonFactory implements CustomerFactory {

	@Override
	public Customer createCustomer(CustomerDTO customerDTO) {
		return NaturalPerson.builder()
	            .taxId(customerDTO.getTaxId())
	            .email(customerDTO.getEmail())
	            .phoneNumber(customerDTO.getPhoneNumber())
	            .address(customerDTO.getAddress())
	            .firstName(customerDTO.getFirstName())
	            .lastName(customerDTO.getLastName())
	            .birthDate(customerDTO.getBirthDate())
	            .build();
	}

	@Override
	public CustomerType getCustomerType() {
		return CustomerType.PHYSICAL;
	}

}
