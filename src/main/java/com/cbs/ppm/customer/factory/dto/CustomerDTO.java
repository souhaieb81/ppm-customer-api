package com.cbs.ppm.customer.factory.dto;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder(builderClassName = "CustomerDtoBuilder")
@ToString
@EqualsAndHashCode
public class CustomerDTO {
	private String taxId;
	private String email;
	private String phoneNumber;
	private String address;

	// NaturalPerson
	private String firstName;
	private String lastName;
	private LocalDate birthDate;

	// LegalEntity
	private String companyName;
	private LocalDate incorporationDate;
	private String legalForm;
}
