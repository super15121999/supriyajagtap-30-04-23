package com.avisys.cim.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avisys.cim.entity.Customer;
import com.avisys.cim.entity.MobileNumber;
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
  public Customer addMobileNumber(Long customerId, MobileNumber mobileNumber) {
      Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer not found"));
      customer.getMobileNumbers().add(mobileNumber);
      mobileNumber.setCustomer(customer);
      return customerRepository.save(customer);
  }

  public void removeMobileNumber(Long customerId, Long mobileNumberId) {
      Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new NoSuchElementException("Customer not found"));
      customer.getMobileNumbers().removeIf(mobileNumber -> mobileNumber.getId().equals(mobileNumberId));
      customerRepository.save(customer);
    }


public Customer getCustomerById(Long id) {
	// TODO Auto-generated method stub
	return null;
}
public void deleteCustomer(String mobileNumber) {
    customerRepository.findByMobileNumbersNumber(mobileNumber).ifPresent(customerRepository::delete);
}



}