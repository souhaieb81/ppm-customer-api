package com.cbs.ppm.customer.model;

import java.time.LocalDate;

import com.cbs.ppm.customer.enums.CustomerType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "natural_persons")
@PrimaryKeyJoinColumn(name = "customer_id")
public class NaturalPerson extends Customer {
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	protected NaturalPerson() {
		super();
	}

	private NaturalPerson(NaturalPersonBuilder builder) {
		super(builder.id, builder.taxId, builder.email, builder.phoneNumber, builder.address, CustomerType.PHYSICAL);
		this.firstName = builder.firstName;
		this.lastName = builder.lastName;
		this.birthDate = builder.birthDate;
	}

	public static class NaturalPersonBuilder extends CustomerBuilder<NaturalPersonBuilder> {
		private String firstName;
		private String lastName;
		private LocalDate birthDate;

		public NaturalPersonBuilder firstName(String firstName) {
			this.firstName = firstName;
			return this;
		}

		public NaturalPersonBuilder lastName(String lastName) {
			this.lastName = lastName;
			return this;
		}

		public NaturalPersonBuilder birthDate(LocalDate birthDate) {
			this.birthDate = birthDate;
			return this;
		}

		public NaturalPerson build() {
			return new NaturalPerson(this);
		}
	}

	public static NaturalPersonBuilder builder() {
		return new NaturalPersonBuilder().customerType(CustomerType.PHYSICAL);
	}
}