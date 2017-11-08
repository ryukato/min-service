package app.api;

import app.MinResourceServiceApplication;
import app.module.currency.api.CurrencyResourceApiImpl;
import app.module.currency.api.CurrencyResourceApi;
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
public class CurrencyResourceApiTest {

    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    @Autowired
    private PageableArgumentResolver pageableArgumentResolver;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        CurrencyResourceApi currencyResourceApi = new CurrencyResourceApiImpl();
        this.mockMvc = MockMvcBuilders.standaloneSetup(currencyResourceApi)
                .setCustomArgumentResolvers(pageableArgumentResolver)
                .setMessageConverters(httpMessageConverters)
                .build();
    }

    @Test
    public void testFindAllCurrencies() throws Exception {
        String content = this.mockMvc.perform(get("/api/v1/currencies")
                .accept(MediaType.APPLICATION_JSON_UTF8))
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
}
