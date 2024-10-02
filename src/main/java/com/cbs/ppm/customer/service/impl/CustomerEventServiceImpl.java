package com.cbs.ppm.customer.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.cbs.ppm.customer.model.Customer;
import com.cbs.ppm.customer.service.CustomerEventService;

@Service
public class CustomerEventServiceImpl implements CustomerEventService {

	@Value("${spring.kafka.topic}")
	private String customerTopic;

	private final KafkaTemplate<String, Object> kafkaTemplate;

	public CustomerEventServiceImpl(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}

	@Override
	public void newCustomerEvent(Customer customer) {
		kafkaTemplate.send(customerTopic, customer);

	}

}
