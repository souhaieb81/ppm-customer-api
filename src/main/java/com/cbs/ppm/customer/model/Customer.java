package com.cbs.ppm.customer.model;

import com.cbs.ppm.customer.enums.CustomerType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "customers")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "tax_id", unique = true, nullable = false)
	private String taxId;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "phone_number")
	private String phoneNumber;

	@Column(name = "address")
	private String address;

	@Enumerated(EnumType.STRING)
	@Column(name = "customer_type", nullable = false)
	private CustomerType customerType;

	public static class CustomerBuilder<T extends CustomerBuilder<T>> {
		protected Long id;
		protected String taxId;
		protected String email;
		protected String phoneNumber;
		protected String address;
		protected CustomerType customerType;

		@SuppressWarnings("unchecked")
		public T id(Long id) {
			this.id = id;
			return (T) this;
		}

		@SuppressWarnings("unchecked")
		public T taxId(String taxId) {
			this.taxId = taxId;
			return (T) this;
		}

		@SuppressWarnings("unchecked")
		public T email(String email) {
			this.email = email;
			return (T) this;
		}

		@SuppressWarnings("unchecked")
		public T phoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
			return (T) this;
		}

		@SuppressWarnings("unchecked")
		public T address(String address) {
			this.address = address;
			return (T) this;
		}

		@SuppressWarnings("unchecked")
		public T customerType(CustomerType customerType) {
			this.customerType = customerType;
			return (T) this;
		}
	}
}
