package app.api;

import app.MinResourceServiceApplication;
import app.module.codes.api.CodesResourceApi;
import app.module.codes.api.CodesResourceApiImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MinResourceServiceApplication.class)
public class CodeResourceApiTest {

    @Autowired
    private HttpMessageConverter[] httpMessageConverters;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        CodesResourceApi codesResourceApi = new CodesResourceApiImpl();
        this.mockMvc = MockMvcBuilders.standaloneSetup(codesResourceApi)
                .setMessageConverters(httpMessageConverters)
                .build();
    }

    @Test
    public void testApplicationTypes() throws Exception {
        mockMvc.perform(get("/api/v1/codes/application-types")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string("[\"ANDROID\",\"APPLE\",\"MS\",\"WEB\"]"))
                .andDo(print());
    }

    @Test
    public void testReceiveMethods() throws Exception {
        mockMvc.perform(get("/api/v1/codes/receive-methods")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string("[\"BANK_ACCOUNT\",\"CASH_PICKUP\",\"DELIVERY\"]"))
                .andDo(print());
    }


    @Test
    public void testServiceMethods() throws Exception {
        mockMvc.perform(get("/api/v1/codes/service-methods")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string("[\"APP\",\"WEB\"]"))
                .andDo(print());
    }

    @Test
    public void testTransferMethods() throws Exception {
        mockMvc.perform(get("/api/v1/codes/transfer-methods")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string("[\"BIT_COIN\",\"MOBILE\",\"ONLINE\"]"))
                .andDo(print());
    }

    @Test
    public void testTransferFeeTypes() throws Exception {
        mockMvc.perform(get("/api/v1/codes/transfer-fee-types")
                .accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(content().string("[\"RATE\",\"AMOUNT\"]"))
                .andDo(print());
    }

}
