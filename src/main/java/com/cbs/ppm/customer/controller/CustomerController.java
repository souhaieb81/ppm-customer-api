package com.cbs.ppm.customer.controller;

import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cbs.ppm.customer.enums.CustomerType;
import com.cbs.ppm.customer.factory.dto.CustomerDTO;
import com.cbs.ppm.customer.model.Customer;
import com.cbs.ppm.customer.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("api/customer")
@Tag(name = "Customer", description = "Customer management APIs")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@CrossOrigin(origins = "*")
	@GetMapping("/list/all")
	@Operation(summary = "Get all customers", description = "Retrieve list of all customers")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "customers list returned successfully", content = @Content(schema = @Schema(implementation = Customer[].class))),
			@ApiResponse(responseCode = "400", description = "Bad request"),
			@ApiResponse(responseCode = "401", description = "Unauthorized"),
			@ApiResponse(responseCode = "403", description = "Forbidden"),
			@ApiResponse(responseCode = "404", description = "Not Found"),
			@ApiResponse(responseCode = "500", description = "Failure") })
	public List<Customer> getAllCustomers() {
		return customerService.getCustomers();
	}

	@CrossOrigin(origins = "*")
	@PostMapping("/add")
	@Operation(summary = "Add a new customer", description = "Creates a new customer based on the provided data")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Customer created successfully", content = @Content(schema = @Schema(implementation = Customer.class))),
			@ApiResponse(responseCode = "400", description = "Invalid input") })
	public ResponseEntity<Customer> addCustomer(
			@Parameter(description = "Customer type", required = true) @RequestParam CustomerType customerType,
			@Parameter(description = "Customer data", required = true) @RequestBody CustomerDTO customerDTO)
			throws BadRequestException {
		try {
			Customer newCustomer = customerService.addCustomer(customerDTO, customerType);
			return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
		} catch (IllegalArgumentException e) {
			throw new BadRequestException("Invalid customer data: " + e.getMessage());
		}
	}
}
