package com.avisys.cim.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.avisys.cim.entity.Customer;
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
  
}
  


	





    
        
    
    
  			
    
    

    
    
    
    
    



    

    
