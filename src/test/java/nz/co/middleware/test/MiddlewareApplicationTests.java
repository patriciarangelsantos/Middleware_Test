package nz.co.middleware.test;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import junit.framework.Assert;
import nz.co.middleware.domain.Company;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
class MiddlewareApplicationTests {

	
	@Autowired
    private TestRestTemplate restTemplate;

	@SuppressWarnings("deprecation")
	@LocalServerPort
    int randomServerPort;
    
    private Company company1, company2;
    
    @Before
    public void setUp() {

        this.company1 = new Company(1L, "SAS", "Test Company 1");
        this.company2 = new Company(2L, "NAN", "Test Company 2");
    }
    
    @Test
    public void testAddCompanySuccess() throws URISyntaxException {
    	
        final String baseUrl = "http://localhost:"+randomServerPort+"/xml-api/company";
        URI uri = new URI(baseUrl);
         
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true");      
 
        HttpEntity<Company> request = new HttpEntity<>(company1, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        Assert.assertEquals(201, result.getStatusCodeValue());
    }
    
    @Test
    public void testAddCompanyMissingHeader() throws URISyntaxException 
    {
        final String baseUrl = "http://localhost:"+randomServerPort+"/xml-api/all";
        URI uri = new URI(baseUrl);
         
        HttpHeaders headers = new HttpHeaders();
 
        HttpEntity<Company> request = new HttpEntity<>(company2, headers);
         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
         
        //Verify bad request and missing header
        Assert.assertEquals(405, result.getStatusCodeValue());
        Assert.assertEquals(true, result.getBody().contains("Missing request header"));
    }

}
