package com.example.todo;

import com.example.todo.model.Task;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetTasksReturnsOk() throws Exception {
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk());
    }

    @Test
    public void testAddTask() throws Exception {
        String newTaskJson = "{\"title\": \"Test task\", \"completed\": false}";

        mockMvc.perform(post("/tasks")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newTaskJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title", is("Test task")))
                .andExpect(jsonPath("$.completed", is(false)));
    }
}