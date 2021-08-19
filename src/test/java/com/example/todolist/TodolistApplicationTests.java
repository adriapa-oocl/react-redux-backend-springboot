package com.example.todolist;

import com.example.todolist.service.TodoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class TodolistApplicationTests {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private TodoService todoService;

	@Test
	void should_return_all_todo_when_getAllTodos() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.get("/todos"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.[*]").isNotEmpty());
	}

	@Test
	void should_create_todo_when_addTodo_given_todo_information() throws Exception {
		String todo = "{\n" +
				"    \"text\": \"dummy todo\"\n" +
				"}";

		mockMvc.perform(MockMvcRequestBuilders.post("/todos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(todo))
				.andExpect(status().isCreated());
	}
}
