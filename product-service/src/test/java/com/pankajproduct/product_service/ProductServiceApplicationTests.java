package com.pankajproduct.product_service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pankajproduct.product_service.dto.ProductRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//no unit test ( integration test , post api or get api working or not
//testcontainer library
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc  // to mock the controller
class ProductServiceApplicationTests {

	/*
	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;
	@Container
	static MongoDBContainer mongoDBContainer=new MongoDBContainer("mongo");
	//above one will download the image of the mondo db docuker image of particular vcrsion

	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
		//set the property of the dynamically at the  creating time of test by defining property
	}


	@Test
	void shouldCreateProduct() throws Exception {
		ProductRequest productRequest=getProductRequest();
		String productRequestString=objectMapper.writeValueAsString(productRequest);
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				.contentType(MediaType.APPLICATION_JSON)
				.content(productRequestString))
				.andExpect(status().isCreated());
	}

	private ProductRequest getProductRequest(){
		return ProductRequest.builder().
				name("iphone13")
				.description("iphone13")
				.price(BigDecimal.valueOf(1200))
				.build();
	}


*/
}
