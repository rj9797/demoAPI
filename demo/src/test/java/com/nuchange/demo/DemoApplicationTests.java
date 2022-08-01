package com.nuchange.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.util.logging.Logger;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.nuchange.demo.controller.Controller;


@ExtendWith(SpringExtension.class)
@WebMvcTest(Controller.class)
class DemoApplicationTests {
	
	@Autowired
	private MockMvc mockMvc;

	
	@Test
	void A() throws Exception{
		try {
			RequestBuilder request= MockMvcRequestBuilders.get("/storeurl?url=irctc.com");
			mockMvc.perform(request).andReturn();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	@Test
	void B() throws Exception{
		try {
			RequestBuilder request= MockMvcRequestBuilders.get("/get?url=irctc.com");
			mockMvc.perform(request).andReturn();
		}catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	void C() throws Exception{
		try {
			RequestBuilder request= MockMvcRequestBuilders.get("/count?url=irctc.com");
			MvcResult result= mockMvc.perform(request).andReturn();
			assertEquals("2", result.getResponse().getContentAsString());
		}catch (Exception e) {
			throw e;
		}
	}
	
	@Test
	void D() throws Exception{
		try {
			RequestBuilder request= MockMvcRequestBuilders.get("/list?page=2&size=2");
			 mockMvc.perform(request).andReturn();
		}catch (Exception e) {
			throw e;
		}
	}

}
