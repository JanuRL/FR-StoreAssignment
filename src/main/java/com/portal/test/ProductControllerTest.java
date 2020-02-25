package com.portal.test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class ProductControllerTest 
{
    @LocalServerPort
    int randomServerPort;
    
    @Test
    public void testGetProductListSuccess() throws URISyntaxException 
    {
    	RestTemplate restTemplate = new RestTemplate();
        
        final String baseUrl = "http://localhost:" + randomServerPort + "/product/findAll";
        URI uri = new URI(baseUrl);
     
        ResponseEntity<List> result = restTemplate.getForEntity(uri, List.class);
         
        Assert.assertEquals(2, result.getBody().size());
    }  
}