package com.azmain.dcs;


import com.azmain.dcs.controller.DocumentTypesController;
import com.azmain.dcs.dao.DocProperties;
import com.azmain.dcs.model.DocumentProperties;
import com.azmain.dcs.model.DocumentTypes;
import com.azmain.dcs.repository.DocumentPropertiesRepository;
import com.azmain.dcs.repository.DocumentTypesRepository;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DocumentTypesControllerTest {

    /**
     * The main part
     */
    MockMvc mockMvc;

    /**
     * Must be autowired, otherwise shows error
     */
    @Autowired
    DocumentTypesController documentTypesController;

    /**
     * mocking the repository
     */
    @MockBean
    DocumentTypesRepository documentTypesRepository;
    @MockBean
    DocumentPropertiesRepository documentPropertiesRepository;

    /**
     * setup part is being called before every test runs
     * to build the mockMvc
     * @throws Exception
     */
    @Before
    public void setUp() throws Exception {

        mockMvc = MockMvcBuilders.standaloneSetup(documentTypesController)
                .build();


    }


    /**
     * test the endpoint  - api/document-types
     * @throws Exception
     */
    @Test
    public void testGetDocumentTypes() throws Exception{

        /**
         * creating an entity & returning it when called
         */
        DocumentTypes documentTypes = new DocumentTypes();
        documentTypes.setTypeName("NID");
        List<DocumentTypes> documentTypesList = Arrays.asList(documentTypes);
        given(documentTypesRepository.findAll()).willReturn(documentTypesList);


        /**
         * calling the endpoint and tested
         */
        mockMvc.perform(get("/api/document-types")
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andExpect(jsonPath("$[0].typeName", Matchers.is("NID")));

    }


    /**
     * test the endpoint - api/document-types/{docId}/properties
     * @throws Exception
     */
    @Test
    public void testGetPropertiesForDocumentType() throws Exception{


        String properties = "{\"name\": \"name\", \"type\": \"input\", \"label\": \"name\", \"value\": null, \"inputType\": \"text\", \"validations\": [{\"name\": \"required\", \"message\": \"Name Required\", \"validator\": \"required\"}]}";

        DocProperties docProperties = new DocProperties(properties);


        List<DocProperties> docPropertiesList = Arrays.asList(docProperties);
        given(documentPropertiesRepository.getListOfProperties(1l))
                .willReturn(docPropertiesList);

        mockMvc.perform(get("/api/document-types/{id}/properties",1)
                .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(1)))
                .andDo(print())
                .andExpect(jsonPath("$[0].properties", Matchers.is(properties)));

    }
}
