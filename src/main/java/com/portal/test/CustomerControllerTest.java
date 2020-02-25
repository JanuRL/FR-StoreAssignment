package com.portal.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.portal.request.OrderRequest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class CustomerControllerTest 
{
    @LocalServerPort
    int randomServerPort;
    
    @Test
    public void testGetCustomerListSuccess() throws URISyntaxException 
    {
    	RestTemplate restTemplate = new RestTemplate();
        
        final String baseUrl = "http://localhost:" + randomServerPort + "/customer/findAll";
        URI uri = new URI(baseUrl);
     
        ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
         
        Assert.assertEquals(3, result.getBody().size());
    }  
    
    @Test
    public void testPlaceOrderSuccess() {
    	RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/customer/requestOrder";
        URI uri = null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
         
        List<String> pList = Arrays.asList("mbl");
        
        OrderRequest or = new OrderRequest();
        or.setCust_id(3);
        or.setProductList(pList);
        
        HttpHeaders headers = new HttpHeaders();
     
        HttpEntity<OrderRequest> request = new HttpEntity<>(or, headers);
         
       	ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
       	Assert.assertEquals(result.getBody(), "OK!");
    }
    
    public void testPlaceOrderError() {
    	RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:"+randomServerPort+"/customer/requestOrder";
        URI uri = null;
		try {
			uri = new URI(baseUrl);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
         
        List<String> pList = Arrays.asList("mbl");
        
        OrderRequest or = new OrderRequest();
        or.setCust_id(1);
        or.setProductList(pList);
        
        HttpHeaders headers = new HttpHeaders();
     
        HttpEntity<OrderRequest> request = new HttpEntity<>(or, headers);
         
       	ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
       	Assert.assertEquals(result.getBody(), "NOT OK! Not enough points.");
    }
}