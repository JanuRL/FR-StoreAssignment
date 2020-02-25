package com.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.portal.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	@Modifying
	@Transactional
    @Query(value = "UPDATE customer set availablePoints = :points where id = :custId", nativeQuery = true)
    int updatePoints(@Param("points") Long points, @Param("custId") Integer customerId);
}
