package com.jmvillel.demo.spacecraft.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jmvillel.demo.spacecraft.domain.SpaceCraft;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@TestMethodOrder(OrderAnnotation.class)
public class SpaceCraftControllerIntegrationTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired 
    private ObjectMapper objectMapper;
	  
	  @Test
	  @Order(1)
	  @DisplayName("/spacecrafts GET endpoint should return the page of spacecrafts and the size requested in page and size params")
	  void testFindPage() throws Exception {
	       
		  mockMvc.perform(get("/spacecrafts?page=0&size=4"))
		  .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.totalPages").value(4));

	  }
	  
	  @Test
	  @Order(2)
	  @DisplayName("/spacecrafts endpoint should return a list of spacecrafts filtered by name with the value requested in query param")
	  void testFindByName() throws Exception {
	       
		  mockMvc.perform(get("/spacecrafts?query=Wing"))
		  .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$[*].name", Matchers.everyItem(Matchers.containsString("Wing"))));

	  }
	  
	  @Test
	  @Order(3)
	  @DisplayName("/spacecrafts/{id} GET endpoint should return a spacecraft with the id requested")
	  void testFindOneById() throws Exception {
	       
		  mockMvc.perform(get("/spacecrafts/1"))
		  .andDo(print())
          .andExpect(status().isOk())
          .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));

	  }
	  
	  @Test
	  @Order(4)
	  @DisplayName("/spacecrafts/{id} POST endpoint should return a spacecraft with the id requested")
	  void testCreate() throws Exception {
		  
		  MvcResult result = mockMvc.perform(post("/spacecrafts")
		    .contentType(MediaType.APPLICATION_JSON)
		    .content("{\"name\": \"SpaceCraft I\"}")
		    .accept(MediaType.APPLICATION_JSON))
		    .andExpect(status().isOk()).andReturn();
		  
		  String body = result.getResponse().getContentAsString();
		  SpaceCraft sc = objectMapper.readValue(body, SpaceCraft.class);
		  
		  assertNotNull(sc.getId());
		  assertEquals(sc.getName(),"SpaceCraft I");

	  }
	  
	  @Test
	  @Order(5)
	  @DisplayName("/spacecrafts PUT endpoint should return a spacecraft with the changes updated")
	  void testUpdate() throws Exception {
		  
		  MvcResult result = mockMvc.perform(put("/spacecrafts")
		    .contentType(MediaType.APPLICATION_JSON)
		    .content("{\"id\": 1,\"name\": \"SpaceCraft I\"}")
		    .accept(MediaType.APPLICATION_JSON))
		    .andExpect(status().isOk()).andReturn();
		  
		  String body = result.getResponse().getContentAsString();
		  SpaceCraft sc = objectMapper.readValue(body, SpaceCraft.class);
		  
		  assertEquals(sc.getId(),1);
		  assertEquals(sc.getName(),"SpaceCraft I");

	  }
	  
	  @Test
	  @Order(6)
	  @DisplayName("/spacecrafts DELETE endpoint should return an id of the spacecraft deleted")
	  void testDelete() throws Exception {
		  
		  MvcResult result = mockMvc.perform(delete("/spacecrafts/1"))
		    .andExpect(status().isOk()).andReturn();
		  
		  String id = result.getResponse().getContentAsString();
		  
		  assertEquals(id,"1");
		  
		  final MvcResult resultEx = mockMvc.perform(get("/spacecrafts/1")).andReturn();
		  
		  assertEquals(resultEx.getResolvedException().getMessage(), "Entity Spacecraft for id 1 was not found.");
	  }
	  
	  
}
