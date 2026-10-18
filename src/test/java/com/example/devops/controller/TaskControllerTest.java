package com.example.devops.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAllTasksReturnsOk() throws Exception {
        mockMvc.perform(get("/api/tasks"))
               .andExpect(status().isOk())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    void getExistingTaskById() throws Exception {
        mockMvc.perform(get("/api/tasks/1"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void getMissingTaskReturns404() throws Exception {
        mockMvc.perform(get("/api/tasks/999"))
               .andExpect(status().isNotFound());
    }

    @Test
    void createTaskReturns201() throws Exception {
        String body = "{\"title\":\"Test CI task\",\"description\":\"Created in test\",\"completed\":false}";
        mockMvc.perform(post("/api/tasks")
               .contentType(MediaType.APPLICATION_JSON).content(body))
               .andExpect(status().isCreated())
               .andExpect(jsonPath("$.title").value("Test CI task"));
    }

    @Test
    void createTaskWithEmptyTitleReturnsBadRequest() throws Exception {
        String body = "{\"title\":\"\",\"description\":\"No title\",\"completed\":false}";
        mockMvc.perform(post("/api/tasks")
               .contentType(MediaType.APPLICATION_JSON).content(body))
               .andExpect(status().isBadRequest());
    }

    @Test
    void healthEndpointReachable() throws Exception {
        mockMvc.perform(get("/actuator/health"))
               .andExpect(status().isOk());
    }
}
