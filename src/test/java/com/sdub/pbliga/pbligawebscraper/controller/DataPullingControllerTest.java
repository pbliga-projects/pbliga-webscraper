package com.sdub.pbliga.pbligawebscraper.controller;

import com.sdub.pbliga.pbligawebscraper.BaseTest;
import com.sdub.pbliga.pbligawebscraper.processor.DataType;
import com.sdub.pbliga.pbligawebscraper.service.DataPullingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(DataPullingController.class)
public class DataPullingControllerTest extends BaseTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataPullingService dataPullingService;

    @Test
    public void shouldReturnVersion() throws Exception {
        this.mockMvc.perform(get("/version")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(is("0.0.1-SNAPSHOT")));
    }

    @Test
    public void getFreeClubs_shouldReturnFreeClubsJsonString() throws Exception {

        String freeClubs = getResourceFileContent("/response/freeClubs.txt");

        when(dataPullingService.pullData(DataType.FreeClubs)).thenReturn(freeClubs);

        this.mockMvc.perform(get("/pull/free-clubs")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(is(freeClubs)));
    }
}
