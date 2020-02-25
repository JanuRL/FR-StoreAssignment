package com.portal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.portal.entity.Customer;
import com.portal.entity.Product;
import com.portal.repository.CustomerRepository;
import com.portal.repository.ProductRepository;
import com.portal.request.OrderRequest;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private ProductRepository productRepository;

	@RequestMapping(value = "/findAll")
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@PostMapping(value = "/placeOrder")
	private String placeOrder(@RequestBody OrderRequest orderRequest) 
	{
		Integer custId = orderRequest.getCustId();
		if (custId == null || !customerRepository.existsById(custId)) {
			return "Please provide a valid customer id.";
		}
		
		List<String> orderList = orderRequest.getProductList();
		if (orderList.isEmpty()) 
		{
			return "Please provide list of items to be ordered.";
		}
		
		String result = null;

		Long totalCost = new Long(0);

		for (String prodCode : orderList) {
			Product product = productRepository.findProduct(prodCode);
			if (product == null) {
				return "Product not found for code:" + prodCode;
			} else {
				totalCost = Long.sum(totalCost, Integer.parseInt(product.getPrice()));
			}
		}

		Long customerAvailablePoints = customerRepository.findById(custId).get().getAvailablePoints();

		if (totalCost.compareTo(Long.valueOf(customerAvailablePoints)) <= 0) {			
			customerAvailablePoints = customerAvailablePoints - totalCost;
			customerRepository.updatePoints(customerAvailablePoints, custId);
			result = "Purchase successful! New available points = " + customerAvailablePoints;
		} else {
			result = "Not enough points!! Available - " + customerAvailablePoints + ". Required - " + totalCost;
		}

		return result;

	}

}
