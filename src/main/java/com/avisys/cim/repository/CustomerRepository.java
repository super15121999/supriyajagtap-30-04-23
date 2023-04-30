package com.avisys.cim.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.avisys.cim.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

  @Query("SELECT c FROM Customer c WHERE (:firstName IS NULL OR LOWER(c.firstName) LIKE LOWER(CONCAT('%', :firstName, '%'))) "
      + "AND (:lastName IS NULL OR LOWER(c.lastName) LIKE LOWER(CONCAT('%', :lastName, '%'))) "
      + "AND (:mobileNumber IS NULL OR c.mobileNumber = :mobileNumber)")
  List<Customer> findCustomersByFilters(String firstName, String lastName, String mobileNumber);
  
  Customer findByMobileNumber(String mobileNumber);
}