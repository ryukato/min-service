package app.module.currency.api;

import app.MinResourceServiceApplication;
import app.currency.model.Currency;
import app.module.currency.entity.CurrencyEntity;
import app.module.currency.repo.CurrencyRepository;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinResourceServiceApplication.class)
public class CurrencyResourceApiTest {

    private final Pageable pageable = new PageRequest(0, 100, null);


    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    @Autowired
    private PageableArgumentResolver pageableArgumentResolver;

    private MockMvc mockMvc;

    @MockBean
    private CurrencyRepository repository;

    @Before
    public void setUp() {
        CurrencyResourceApi currencyResourceApi = new CurrencyResourceApiImpl(repository);
        this.mockMvc = MockMvcBuilders.standaloneSetup(currencyResourceApi)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setMessageConverters(httpMessageConverters)
                .build();
    }

    @Test
    public void testFindAllCurrencies() throws Exception {
        given(repository.findAll(pageable)).willReturn(new PageImpl<>(Arrays.asList(buildTestCurrencyEntity())));
        MvcResult result = this.mockMvc.perform(get("/api/v1/currencies")
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
        given(repository.findById(testId)).willReturn(buildTestCurrencyEntity());
        MvcResult result = this.mockMvc.perform(get("/api/v1/currencies/" + testId)
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
    public void testFindByName() throws Exception {
        String testName = "Korea";
        given(repository.findByName(testName)).willReturn(buildTestCurrencyEntity());
        MvcResult result = this.mockMvc.perform(get("/api/v1/currencies/search/by-name")
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
    public void testFindByKoreanName() throws Exception {
        String testName = "Korea";
        given(repository.findByKoreanName(testName)).willReturn(buildTestCurrencyEntity());
        MvcResult result = this.mockMvc.perform(get("/api/v1/currencies/search/by-korean-name")
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

    private void assertContentNotEmpty(String content) {
        assertNotNull(content);
        assertTrue(!content.isEmpty());
        assertTrue(content.contains("currency"));
    }

    private CurrencyEntity buildTestCurrencyEntity() {
        BigDecimal sampleCurrency = new BigDecimal("1.111");
        Currency currency = Currency.builder()
                .withBaseDateTime(LocalDateTime.now())
                .withCurrency("USD")
                .withCurrencyInKorean("달러")
                .withBuyInCashCurrency(sampleCurrency)
                .withBuyInCashSpread(sampleCurrency)
                .withSellInCashCurrency(sampleCurrency)
                .withSellInCashSpread(sampleCurrency)
                .withSellInWireCurrency(sampleCurrency)
                .withTravelerCheckCurrency(sampleCurrency)
                .withForeignCheckCurrency(sampleCurrency)
                .withSellingBaseRate(sampleCurrency)
                .withCurrencyInDollar(sampleCurrency)
                .build();
        return new CurrencyEntity(currency);
    }
}
