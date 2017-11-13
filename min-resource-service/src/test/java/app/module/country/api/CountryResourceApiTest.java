package app.module.country.api;

import app.MinResourceServiceApplication;
import app.country.model.Country;
import app.module.country.entity.CountryEntity;
import app.module.country.repo.CountryRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinResourceServiceApplication.class)
public class CountryResourceApiTest {

    private final Pageable pageable = new PageRequest(0, 100, null);

    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    @Autowired
    private PageableArgumentResolver pageableArgumentResolver;

    private MockMvc mockMvc;

    @MockBean
    private CountryRepository repository;
    @Before
    public void setUp() {
        CountryResourceApi countryResourceApi = new CountryResourceApiImpl(repository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(countryResourceApi)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setMessageConverters(httpMessageConverters)
                .build();
    }

    @Test
    public void testFindAllCountries() throws Exception {
        given(repository.findAll(pageable)).willReturn(new PageImpl<>(Arrays.asList(buildTestCountryEntity())));
        MvcResult result = this.mockMvc.perform(get("/api/v1/countries")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.request().asyncStarted())
                .andReturn();
        String content = mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();
        assertContentNotEmpty(content);
    }



    @Test
    public void testFindById() throws Exception {
        String testId = "59aea4483d4a9e4199780dc3";
        given(repository.findById(testId)).willReturn(buildTestCountryEntity());
        MvcResult result = this.mockMvc.perform(get("/api/v1/countries/" + testId)
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.request().asyncStarted())
                .andReturn();
        String content = mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk()).andDo(print())
                .andReturn().getResponse().getContentAsString();
        assertContentNotEmpty(content);
    }

    @Test
    public void testFindByName() throws Exception {
        String testName = "Korea";
        given(repository.findByName(testName)).willReturn(buildTestCountryEntity());
        MvcResult result = this.mockMvc.perform(get("/api/v1/countries/search/by-name")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("name", testName))
                .andExpect(MockMvcResultMatchers.request().asyncStarted())
                .andReturn();
        String content = mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertContentNotEmpty(content);
    }

    @Test
    public void testFindByNameLike() throws Exception {
        String testName = "Korea";
        given(repository.findByNameLike(testName, pageable)).willReturn(new PageImpl<>(Arrays.asList(buildTestCountryEntity())));
        MvcResult result = this.mockMvc.perform(get("/api/v1/countries/search/by-name-like")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("name", testName))
                .andExpect(MockMvcResultMatchers.request().asyncStarted())
                .andReturn();
        String content = mockMvc.perform(asyncDispatch(result))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn().getResponse().getContentAsString();

        assertContentNotEmpty(content);
    }

    @Test
    public void testFindByIso() throws Exception {
        String testIso = "KR";
        given(repository.findByIso(testIso)).willReturn(buildTestCountryEntity());
        MvcResult result = this.mockMvc.perform(get("/api/v1/countries/search/by-iso")
                .accept(MediaType.APPLICATION_JSON_UTF8)
                .param("iso", testIso))
                .andExpect(MockMvcResultMatchers.request().asyncStarted())
                .andReturn();
        String content = mockMvc.perform(asyncDispatch(result))
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

    private CountryEntity buildTestCountryEntity() {
        Country country = Country
                .builder()
                .withName("Korea")
                .withIsoCode("KR")
                .withCurrencies(Arrays.asList("KRW"))
                .build();
        return new CountryEntity(country);
    }
}
