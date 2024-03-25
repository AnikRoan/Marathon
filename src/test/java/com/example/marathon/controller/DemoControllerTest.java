package com.example.marathon.controller;

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
class DemoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("""
            When calling the /hello endpoint we should get back 
            in the response body ant an http of 200 ok
            """)
    @WithMockUser
//create mock security context
        //I can use  @WithMockUser with
        //String value() default "user";
        //
        //    String username() default "";
        //
        //    String[] roles() default {"USER"};
        //
        //    String[] authorities() default {};
        //
        //    String password() default "password";
    void helloAuthenticated() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string("Hello"));
    }

    @Test
    @DisplayName("""
            When calling the /hello endpoint unauthenticated we should get back 
            in the response body ant an http of 401 ok
            """)
    void helloUnAuthenticated() throws Exception {
        mockMvc.perform(get("/hello"))
                .andExpect(status().isUnauthorized());
    }
}