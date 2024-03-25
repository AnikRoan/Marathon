package com.example.marathon;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@SpringBootTest
@AutoConfigureMockMvc
class MarathonApplicationTests {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("""
            when /demo endpoint is called without authentication 
            we expect a HTTP response of 401
            """)
    void demoEndpointWithoutAuthenticationTest() throws Exception {
        mockMvc.perform(get("/demo"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @DisplayName("""
            when /demo endpoint is called with authentication but wrong authority
            we expect a HTTP response of 403
            """)
    @WithMockUser(authorities = "wrong")
    void demoEndpointWithAuthenticationButWrongAuth() throws Exception {
        mockMvc.perform(get("/demo"))
                .andExpect(status().isForbidden());
    }

    @Test
    @DisplayName("""
            when /demo endpoint is called with authentication  and correct authority
            we expect a HTTP response of 200
            """)
    @WithMockUser(username = "bill", password = "12345", authorities = "read")
    void demoEndpointWithAuthenticationAndCorrectAuth() throws Exception {
        mockMvc.perform(get("/demo"))
                .andExpect(status().isOk())
                .andExpect(content().string("DEMO"));
    }

}
