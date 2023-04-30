package com.avisys.cim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avisys.cim.entity.Customer;
import com.avisys.cim.entity.MobileNumber;
import com.avisys.cim.service.CustomerCreationException;
import com.avisys.cim.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {

  @Autowired
  private CustomerService customerService;

  @GetMapping
  public List<Customer> getCustomers(
      @RequestParam(required = false) String firstName,
      @RequestParam(required = false) String lastName,
      @RequestParam(required = false) String mobileNumber) {
    return customerService.getCustomers(firstName, lastName, mobileNumber);
  }
  @PostMapping
  public ResponseEntity<?> createCustomer(@RequestBody Customer customer) {
    try {
      Customer customer1 = customerService.createCustomer(customer1);
      return ResponseEntity.status(HttpStatus.CREATED).body(customer1);
    } catch (CustomerCreationException ex) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }
  }
  
  // Modify the application to support multiple mobile number for a single customer
  @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
      Customer customer = customerService.getCustomerById(id);
      return ResponseEntity.ok(customer);
  }

  // Modify the application to be able to save a customer with multiple mobile number over REST API
  @PostMapping(value = "/{id}/mobile-numbers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Customer> addMobileNumbersToCustomer(@PathVariable Long id, @RequestBody List<String> mobileNumbers) {
      Customer customer = customerService.addMobileNumber(id, (MobileNumber) mobileNumbers);
      return ResponseEntity.ok(customer);
  }

//Ability to delete over REST API
  @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Void> deleteCustomerById(@PathVariable Long id) {
      customerService.getCustomerById(id);
      return ResponseEntity.noContent().build();
  }
  
//Modify the application to be able to save a customer with multiple mobile number over REST API
  @PostMapping(value = "/{id}/mobile-numbers", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Customer> addMobileNumbersToCustomer1(@PathVariable Long id, @RequestBody List<String> mobileNumbers) {
      Customer customer = customerService.addMobileNumber(id, mobileNumbers);
      return ResponseEntity.ok(customer);
  }


}
  


	





    
        
    
    
  			
    
    

    
    
    
    
    



    

    
