package com.azmain.dcs;

import com.azmain.dcs.controller.DocumentTypesController;
import com.azmain.dcs.model.DocumentTypes;
import com.azmain.dcs.repository.DocumentTypesRepository;
import com.azmain.dcs.repository.DocumentsRepository;
import com.azmain.dcs.service.DocumentTypesService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.validation.constraints.AssertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DcsApplicationTests {

	MockMvc mockMvc;

//	@Autowired
//	protected WebApplicationContext wac;

	@Autowired
	DocumentTypesController documentTypesController;

	@MockBean
	DocumentTypesRepository documentTypesRepository;


	@Test
	public void contextLoads() {
	}

	@Before
	public void setUp() throws Exception {

		this.mockMvc = standaloneSetup(this.documentTypesController)
				.build();
	}

	@Test
	public void testSample(){

		assertThat("hello").isEqualTo("hello");
	}

	@Test
	public void testDocumentTypes() throws Exception{
		mockMvc.perform(get("/api/document-types"))
				.andExpect(status().isOk());

	}
}
