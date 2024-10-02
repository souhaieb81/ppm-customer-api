package com.cbs.ppm.customer.factory;

import org.springframework.stereotype.Component;

import com.cbs.ppm.customer.enums.CustomerType;
import com.cbs.ppm.customer.factory.dto.CustomerDTO;
import com.cbs.ppm.customer.model.Customer;
import com.cbs.ppm.customer.model.LegalEntity;

@Component
public class LegalEntityFactory implements CustomerFactory {

	@Override
	public Customer createCustomer(CustomerDTO customerDTO) {
		return LegalEntity.builder().taxId(customerDTO.getTaxId()).email(customerDTO.getEmail())
				.phoneNumber(customerDTO.getPhoneNumber()).address(customerDTO.getAddress())
				.companyName(customerDTO.getCompanyName()).incorporationDate(customerDTO.getIncorporationDate())
				.legalForm(customerDTO.getLegalForm()).build();
	}

	@Override
	public CustomerType getCustomerType() {
		return CustomerType.MORAL;
	}

}
