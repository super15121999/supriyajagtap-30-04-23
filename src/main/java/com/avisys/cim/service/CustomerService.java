package com.avisys.cim.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.entity.Customer;
import com.avisys.cim.repository.CustomerRepository;

@Service
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  public List<Customer> getCustomers(String firstName, String lastName, String mobileNumber) {
    return customerRepository.findCustomersByFilters(firstName, lastName, mobileNumber);
  }
  
  public Customer createCustomer(Customer customerDto) throws CustomerCreationException {
	    Customer customer = customerRepository.findByMobileNumber(customerDto.getMobileNumber());
	    if (customer != null) {
	      throw new CustomerCreationException("Unable to create Customer. Mobile number already present.");
	    }
	    customer = new Customer();
	    customer.setFirstName(customerDto.getFirstName());
	    customer.setLastName(customerDto.getLastName());
	    customer.setMobileNumber(customerDto.getMobileNumber());
	    return customerRepository.save(customer);
	  }
}