package com.trilogyed.tasker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.tasker.exceptions.JdbcOperationFailedException;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.service.TaskerServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskerController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class TaskerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaskerServiceLayer service;

    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void setUp() throws Exception{
        setupServiceLayerMock();
    }

    @Test
    public void shouldAddTaskAndReturnStatusCreated() throws Exception{
        TaskViewModel inputTask1 = new TaskViewModel();
        inputTask1.setDescription("Do the thing.");
        inputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        inputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        inputTask1.setCategory("Todo");

        TaskViewModel inputTask2 = new TaskViewModel();
        inputTask2.setDescription("Buy the thing.");
        inputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        inputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        inputTask2.setCategory("Grocery List");

        TaskViewModel inputTask3 = new TaskViewModel();
        inputTask3.setDescription("Do the other thing.");
        inputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        inputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        inputTask3.setCategory("Todo");

        TaskViewModel outputTask1 = new TaskViewModel();
        outputTask1.setId(1);
        outputTask1.setDescription("Do the thing.");
        outputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        outputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        outputTask1.setCategory("Todo");

        TaskViewModel outputTask2 = new TaskViewModel();
        outputTask2.setId(2);
        outputTask2.setDescription("Buy the thing.");
        outputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        outputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        outputTask2.setCategory("Grocery List");

        TaskViewModel outputTask3 = new TaskViewModel();
        outputTask3.setId(3);
        outputTask3.setDescription("Do the other thing.");
        outputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        outputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        outputTask3.setCategory("Todo");

        String inputForTest1 = mapper.writeValueAsString(inputTask1);
        String inputForTest2 = mapper.writeValueAsString(inputTask2);
        String inputForTest3 = mapper.writeValueAsString(inputTask3);

        String expectedOutputForTest1 = mapper.writeValueAsString(outputTask1);
        String expectedOutputForTest2 = mapper.writeValueAsString(outputTask2);
        String expectedOutputForTest3 = mapper.writeValueAsString(outputTask3);

        //Act and Assert
        mockMvc.perform(
                post("/tasks")
                        .content(inputForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutputForTest1));

        mockMvc.perform(
                post("/tasks")
                        .content(inputForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutputForTest2));

        mockMvc.perform(
                post("/tasks")
                        .content(inputForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutputForTest3));
    }

    @Test
    public void shouldFindTaskByIdAndReturnStatusOk() throws Exception{
        String test1 = "/tasks/1";
        String test2 = "/tasks/2";
        String test3 = "/tasks/3";

        TaskViewModel outputTask1 = new TaskViewModel();
        outputTask1.setId(1);
        outputTask1.setDescription("Do the thing.");
        outputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        outputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        outputTask1.setCategory("Todo");

        TaskViewModel outputTask2 = new TaskViewModel();
        outputTask2.setId(2);
        outputTask2.setDescription("Buy the thing.");
        outputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        outputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        outputTask2.setCategory("Grocery List");

        TaskViewModel outputTask3 = new TaskViewModel();
        outputTask3.setId(3);
        outputTask3.setDescription("Do the other thing.");
        outputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        outputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        outputTask3.setCategory("Todo");

        String expectedOutputForTest1 = mapper.writeValueAsString(outputTask1);
        String expectedOutputForTest2 = mapper.writeValueAsString(outputTask2);
        String expectedOutputForTest3 = mapper.writeValueAsString(outputTask3);

        mockMvc.perform(
                get(test1)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest1));

        mockMvc.perform(
                get(test2)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest2));

        mockMvc.perform(
                get(test3)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest3));

    }

    @Test
    public void shouldGetAllTasksAndStatusOk() throws Exception{
        TaskViewModel outputTask1 = new TaskViewModel();
        outputTask1.setId(1);
        outputTask1.setDescription("Do the thing.");
        outputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        outputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        outputTask1.setCategory("Todo");

        TaskViewModel outputTask2 = new TaskViewModel();
        outputTask2.setId(2);
        outputTask2.setDescription("Buy the thing.");
        outputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        outputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        outputTask2.setCategory("Grocery List");

        TaskViewModel outputTask3 = new TaskViewModel();
        outputTask3.setId(3);
        outputTask3.setDescription("Do the other thing.");
        outputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        outputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        outputTask3.setCategory("Todo");

        List<TaskViewModel> allOutputTasks = new ArrayList<>();
        allOutputTasks.add(outputTask1);
        allOutputTasks.add(outputTask2);
        allOutputTasks.add(outputTask3);

        String expectedOutputFromTest = mapper.writeValueAsString(allOutputTasks);

        mockMvc.perform(
                get("/tasks")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputFromTest));

    }

    @Test
    public void shouldGetTasksByCategoryAndStatusOk() throws Exception{
        String test1 = "/tasks/category/Todo";
        String test2 = "/tasks/category/Grocery List";

        TaskViewModel outputTask1 = new TaskViewModel();
        outputTask1.setId(1);
        outputTask1.setDescription("Do the thing.");
        outputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        outputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        outputTask1.setCategory("Todo");

        TaskViewModel outputTask2 = new TaskViewModel();
        outputTask2.setId(2);
        outputTask2.setDescription("Buy the thing.");
        outputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        outputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        outputTask2.setCategory("Grocery List");

        TaskViewModel outputTask3 = new TaskViewModel();
        outputTask3.setId(3);
        outputTask3.setDescription("Do the other thing.");
        outputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        outputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        outputTask3.setCategory("Todo");

        List<TaskViewModel> todoTasks = new ArrayList<>();
        todoTasks.add(outputTask1);
        todoTasks.add(outputTask3);

        List<TaskViewModel> groceryListTasks = new ArrayList<>();
        groceryListTasks.add(outputTask2);

        String expectedOutputFromTest1 = mapper.writeValueAsString(todoTasks);
        String expectedOutputFromTest2 = mapper.writeValueAsString(groceryListTasks);

        mockMvc.perform(
                get(test1)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputFromTest1));

        mockMvc.perform(
                get(test2)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputFromTest2));

    }

    @Test
    public void shouldReturnStatusOkWhenPassedAValidTaskToUpdate() throws Exception{
        TaskViewModel outputTask1 = new TaskViewModel();
        outputTask1.setId(1);
        outputTask1.setDescription("Do the thing.");
        outputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        outputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        outputTask1.setCategory("Todo");

        TaskViewModel outputTask2 = new TaskViewModel();
        outputTask2.setId(2);
        outputTask2.setDescription("Buy the thing.");
        outputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        outputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        outputTask2.setCategory("Grocery List");

        TaskViewModel outputTask3 = new TaskViewModel();
        outputTask3.setId(3);
        outputTask3.setDescription("Do the other thing.");
        outputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        outputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        outputTask3.setCategory("Todo");

        String inputForTest1 = mapper.writeValueAsString(outputTask1);
        String inputForTest2 = mapper.writeValueAsString(outputTask2);
        String inputForTest3 = mapper.writeValueAsString(outputTask3);

        //Act and Assert
        mockMvc.perform(
                put("/tasks")
                        .content(inputForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/tasks")
                        .content(inputForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/tasks")
                        .content(inputForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusNoContentWhenPassedAValidIndexToDelete() throws Exception{
        //Arrange
        String test1 = "/tasks/1";
        String test2 = "/tasks/2";
        String test3 = "/tasks/3";

        //Act and Assert
        mockMvc.perform(
                delete(test1)
        )
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc.perform(
                delete(test2)
        )
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc.perform(
                delete(test3)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldThrowUnprocessableEntityStatusWhenInvalidTaskIsSentToAddOrUpdate() throws Exception{
        TaskViewModel inputTask1 = new TaskViewModel();
        inputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        inputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        inputTask1.setCategory("Todo");

        TaskViewModel inputTask2 = new TaskViewModel();
        inputTask2.setDescription("Buy the thing.");
        inputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        inputTask2.setCategory("Grocery List");

        TaskViewModel inputTask3 = new TaskViewModel();
        inputTask3.setDescription("Do the other thing.");
        inputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        inputTask3.setCategory("Todo");

        String inputForTest1 = mapper.writeValueAsString(inputTask1);
        String inputForTest2 = mapper.writeValueAsString(inputTask2);
        String inputForTest3 = mapper.writeValueAsString(inputTask3);

        mockMvc.perform(
                post("/tasks")
                .content(inputForTest1)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/tasks")
                        .content(inputForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/tasks")
                        .content(inputForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/tasks")
                        .content(inputForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/tasks")
                        .content(inputForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/tasks")
                        .content(inputForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

    @Test
    public void shouldReturnStatusNotFoundWhenPassedInvalidIdToUpdateDeleteOrGetByIdMethods() throws Exception{
        //Arrange
        String test1 = "/tasks/4";
        String test2 = "/tasks/6";

        TaskViewModel taskWithInvalidId = new TaskViewModel();
        taskWithInvalidId.setId(5);
        taskWithInvalidId.setDescription("Do the other thing.");
        taskWithInvalidId.setCreateDate(LocalDate.of(2021, 2, 10));
        taskWithInvalidId.setDueDate(LocalDate.of(2021, 2, 17));
        taskWithInvalidId.setCategory("Todo");

        String inputForTest3 = mapper.writeValueAsString(taskWithInvalidId);

        //Act and Assert
        mockMvc.perform(
                get(test1)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(
                delete(test2)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(
                put("/tasks")
                .content(inputForTest3)
                .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

    }

    private void setupServiceLayerMock() throws JdbcOperationFailedException{
        TaskViewModel inputTask1 = new TaskViewModel();
        inputTask1.setDescription("Do the thing.");
        inputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        inputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        inputTask1.setCategory("Todo");

        TaskViewModel inputTask2 = new TaskViewModel();
        inputTask2.setDescription("Buy the thing.");
        inputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        inputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        inputTask2.setCategory("Grocery List");

        TaskViewModel inputTask3 = new TaskViewModel();
        inputTask3.setDescription("Do the other thing.");
        inputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        inputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        inputTask3.setCategory("Todo");

        TaskViewModel outputTask1 = new TaskViewModel();
        outputTask1.setId(1);
        outputTask1.setDescription("Do the thing.");
        outputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        outputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        outputTask1.setCategory("Todo");

        TaskViewModel outputTask2 = new TaskViewModel();
        outputTask2.setId(2);
        outputTask2.setDescription("Buy the thing.");
        outputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        outputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        outputTask2.setCategory("Grocery List");

        TaskViewModel outputTask3 = new TaskViewModel();
        outputTask3.setId(3);
        outputTask3.setDescription("Do the other thing.");
        outputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        outputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        outputTask3.setCategory("Todo");

        TaskViewModel taskWithInvalidId = new TaskViewModel();
        taskWithInvalidId.setId(5);
        taskWithInvalidId.setDescription("Do the other thing.");
        taskWithInvalidId.setCreateDate(LocalDate.of(2021, 2, 10));
        taskWithInvalidId.setDueDate(LocalDate.of(2021, 2, 17));
        taskWithInvalidId.setCategory("Todo");

        List<TaskViewModel> allTasks = new ArrayList<>();
        allTasks.add(outputTask1);
        allTasks.add(outputTask2);
        allTasks.add(outputTask3);

        List<TaskViewModel> todoTasks = new ArrayList<>();
        todoTasks.add(outputTask1);
        todoTasks.add(outputTask3);

        List<TaskViewModel> groceryListTasks = new ArrayList<>();
        groceryListTasks.add(outputTask2);

        doReturn(outputTask1).when(service).createNewTask(inputTask1);
        doReturn(outputTask2).when(service).createNewTask(inputTask2);
        doReturn(outputTask3).when(service).createNewTask(inputTask3);
        doReturn(outputTask1).when(service).fetchTask(1);
        doReturn(outputTask2).when(service).fetchTask(2);
        doReturn(outputTask3).when(service).fetchTask(3);
        doThrow(JdbcOperationFailedException.class).when(service).fetchTask(4);
        doThrow(JdbcOperationFailedException.class).when(service).updateTask(taskWithInvalidId);
        doThrow(JdbcOperationFailedException.class).when(service).deleteTask(6);
        doReturn(allTasks).when(service).fetchAllTasks();
        doReturn(todoTasks).when(service).fetchTasksByCategory("Todo");
        doReturn(groceryListTasks).when(service).fetchTasksByCategory("Grocery List");
    }

}