package com.cbs.ppm.customer.configuration.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Value("${spring.kafka.topic}")
	private String customerTopic;

	@Bean
	public NewTopic customerTopic() {
		return TopicBuilder.name(customerTopic).build();
	}
}
