package app.module.country.api;

import app.MinResourceServiceApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinResourceServiceApplication.class)
public class CountryResourceApiTest {
    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    @Autowired
    private PageableArgumentResolver pageableArgumentResolver;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        CountryResourceApi countryResourceApi = new CountryResourceApiImpl();
        this.mockMvc = MockMvcBuilders.standaloneSetup(countryResourceApi)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setMessageConverters(httpMessageConverters)
                .build();
    }

    @Test
    public void testFindAllCountries() throws Exception {
        String content = this.mockMvc.perform(get("/api/v1/countries")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        assertContentNotEmpty(content);
    }

    @Test
    public void testFindById() throws Exception {
        String testId = "59aea4483d4a9e4199780dc3";
        String content = this.mockMvc.perform(get("/api/v1/countries/" + testId)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk()).andDo(print())
                .andReturn().getResponse().getContentAsString();
        assertContentNotEmpty(content);
    }

    @Test
    public void testFindByName() throws Exception {
        String testName = "Korea";
        String content = this.mockMvc.perform(get("/api/v1/countries/search/by-name")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("name", testName))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertContentNotEmpty(content);
    }

    @Test
    public void testFindByNameLike() throws Exception {
        String testName = "Korea";
        String content = this.mockMvc.perform(get("/api/v1/countries/search/by-name-like")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("name", testName))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertContentNotEmpty(content);
    }

    @Test
    public void testFindByIso() throws Exception {
        String testIso = "KR";
        String content = this.mockMvc.perform(get("/api/v1/countries/search/by-iso")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("iso", testIso))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertContentNotEmpty(content);
    }

    private void assertContentNotEmpty(String content) {
        assertNotNull(content);
        assertTrue(!content.isEmpty());
        assertTrue(content.contains("country"));
    }
}
