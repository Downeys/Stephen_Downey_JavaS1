package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.dao.TaskerDaoJdbcTemplateImpl;
import com.trilogyed.tasker.dao.TaskerDaoJdbcTemplateImplTest;
import com.trilogyed.tasker.exceptions.JdbcOperationFailedException;
import com.trilogyed.tasker.feign.AdServiceFeignClient;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doReturn;

public class TaskerServiceLayerTest {

    private TaskerServiceLayer service;
    private TaskerDao dao;
    private AdServiceFeignClient client;

    @Before
    public void setUp() throws Exception{
        setupMockDao();
        setupMockFeignClient();

        service = new TaskerServiceLayer(dao, client);
    }

    @Test
    public void shouldFetchTask() throws JdbcOperationFailedException{
        TaskViewModel expectedOutputTask1 = new TaskViewModel();
        expectedOutputTask1.setId(1);
        expectedOutputTask1.setDescription("Do the thing.");
        expectedOutputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        expectedOutputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        expectedOutputTask1.setCategory("Todo");
        expectedOutputTask1.setAdvertisement("Click HERE to qualify for a new car loan!");

        TaskViewModel expectedOutputTask2 = new TaskViewModel();
        expectedOutputTask2.setId(2);
        expectedOutputTask2.setDescription("Buy the thing.");
        expectedOutputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        expectedOutputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        expectedOutputTask2.setCategory("Grocery List");
        expectedOutputTask2.setAdvertisement("Click HERE to qualify for a new car loan!");

        TaskViewModel expectedOutputTask3 = new TaskViewModel();
        expectedOutputTask3.setId(3);
        expectedOutputTask3.setDescription("Do the other thing.");
        expectedOutputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        expectedOutputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        expectedOutputTask3.setCategory("Todo");
        expectedOutputTask3.setAdvertisement("Click HERE to qualify for a new car loan!");

        //Act
        TaskViewModel actualOutputFromTest1 = service.fetchTask(1);
        TaskViewModel actualOutputFromTest2 = service.fetchTask(2);
        TaskViewModel actualOutputFromTest3 = service.fetchTask(3);

        //Assert
        assertEquals(expectedOutputTask1, actualOutputFromTest1);
        assertEquals(expectedOutputTask2, actualOutputFromTest2);
        assertEquals(expectedOutputTask3, actualOutputFromTest3);

    }

    @Test
    public void shouldFetchAllTasks() {
        TaskViewModel expectedOutputTask1 = new TaskViewModel();
        expectedOutputTask1.setId(1);
        expectedOutputTask1.setDescription("Do the thing.");
        expectedOutputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        expectedOutputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        expectedOutputTask1.setCategory("Todo");
        expectedOutputTask1.setAdvertisement("Click HERE to qualify for a new car loan!");

        TaskViewModel expectedOutputTask2 = new TaskViewModel();
        expectedOutputTask2.setId(2);
        expectedOutputTask2.setDescription("Buy the thing.");
        expectedOutputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        expectedOutputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        expectedOutputTask2.setCategory("Grocery List");
        expectedOutputTask2.setAdvertisement("Click HERE to qualify for a new car loan!");

        TaskViewModel expectedOutputTask3 = new TaskViewModel();
        expectedOutputTask3.setId(3);
        expectedOutputTask3.setDescription("Do the other thing.");
        expectedOutputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        expectedOutputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        expectedOutputTask3.setCategory("Todo");
        expectedOutputTask3.setAdvertisement("Click HERE to qualify for a new car loan!");

        List<TaskViewModel> expectedListOfAllTasks = new ArrayList<>();
        expectedListOfAllTasks.add(expectedOutputTask1);
        expectedListOfAllTasks.add(expectedOutputTask2);
        expectedListOfAllTasks.add(expectedOutputTask3);

        //Act
        List<TaskViewModel> actualListOfAllTasks = service.fetchAllTasks();

        //Assert
        assertEquals(3, actualListOfAllTasks.size());
        assertEquals(expectedListOfAllTasks, actualListOfAllTasks);

    }

    @Test
    public void shouldFetchTasksByCategory() {
        TaskViewModel expectedOutputTask1 = new TaskViewModel();
        expectedOutputTask1.setId(1);
        expectedOutputTask1.setDescription("Do the thing.");
        expectedOutputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        expectedOutputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        expectedOutputTask1.setCategory("Todo");
        expectedOutputTask1.setAdvertisement("Click HERE to qualify for a new car loan!");

        TaskViewModel expectedOutputTask2 = new TaskViewModel();
        expectedOutputTask2.setId(2);
        expectedOutputTask2.setDescription("Buy the thing.");
        expectedOutputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        expectedOutputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        expectedOutputTask2.setCategory("Grocery List");
        expectedOutputTask2.setAdvertisement("Click HERE to qualify for a new car loan!");

        TaskViewModel expectedOutputTask3 = new TaskViewModel();
        expectedOutputTask3.setId(3);
        expectedOutputTask3.setDescription("Do the other thing.");
        expectedOutputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        expectedOutputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        expectedOutputTask3.setCategory("Todo");
        expectedOutputTask3.setAdvertisement("Click HERE to qualify for a new car loan!");

        List<TaskViewModel> expectedListOfTodoTasks = new ArrayList<>();
        expectedListOfTodoTasks.add(expectedOutputTask1);
        expectedListOfTodoTasks.add(expectedOutputTask3);

        List<TaskViewModel> expectedListOfGroceryListTasks = new ArrayList<>();
        expectedListOfGroceryListTasks.add(expectedOutputTask2);

        //Act
        List<TaskViewModel> actualListOfTodoTasks = service.fetchTasksByCategory("Todo");
        List<TaskViewModel> actualListOfGroceryListTasks = service.fetchTasksByCategory("Grocery List");

        //Assert
        assertEquals(2, actualListOfTodoTasks.size());
        assertEquals(expectedListOfTodoTasks, actualListOfTodoTasks);

        assertEquals(1, actualListOfGroceryListTasks.size());
        assertEquals(expectedListOfGroceryListTasks, actualListOfGroceryListTasks);

    }

    @Test
    public void shouldCreateNewTask() {
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

        TaskViewModel expectedOutputTask1 = new TaskViewModel();
        expectedOutputTask1.setId(1);
        expectedOutputTask1.setDescription("Do the thing.");
        expectedOutputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        expectedOutputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        expectedOutputTask1.setCategory("Todo");
        expectedOutputTask1.setAdvertisement("Click HERE to qualify for a new car loan!");

        TaskViewModel expectedOutputTask2 = new TaskViewModel();
        expectedOutputTask2.setId(2);
        expectedOutputTask2.setDescription("Buy the thing.");
        expectedOutputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        expectedOutputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        expectedOutputTask2.setCategory("Grocery List");
        expectedOutputTask2.setAdvertisement("Click HERE to qualify for a new car loan!");

        TaskViewModel expectedOutputTask3 = new TaskViewModel();
        expectedOutputTask3.setId(3);
        expectedOutputTask3.setDescription("Do the other thing.");
        expectedOutputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        expectedOutputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        expectedOutputTask3.setCategory("Todo");
        expectedOutputTask3.setAdvertisement("Click HERE to qualify for a new car loan!");

        //Act
        TaskViewModel actualOutputFromTest1 = service.createNewTask(inputTask1);
        TaskViewModel actualOutputFromTest2 = service.createNewTask(inputTask2);
        TaskViewModel actualOutputFromTest3 = service.createNewTask(inputTask3);

        //Assert
        assertEquals(expectedOutputTask1, actualOutputFromTest1);
        assertEquals(expectedOutputTask2, actualOutputFromTest2);
        assertEquals(expectedOutputTask3, actualOutputFromTest3);

    }

    private void setupMockDao() throws JdbcOperationFailedException{
        dao = mock(TaskerDaoJdbcTemplateImpl.class);

        Task inputTask1 = new Task();
        inputTask1.setDescription("Do the thing.");
        inputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        inputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        inputTask1.setCategory("Todo");

        Task inputTask2 = new Task();
        inputTask2.setDescription("Buy the thing.");
        inputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        inputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        inputTask2.setCategory("Grocery List");

        Task inputTask3 = new Task();
        inputTask3.setDescription("Do the other thing.");
        inputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        inputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        inputTask3.setCategory("Todo");

        Task outputTask1 = new Task();
        outputTask1.setId(1);
        outputTask1.setDescription("Do the thing.");
        outputTask1.setCreateDate(LocalDate.of(2021, 1, 23));
        outputTask1.setDueDate(LocalDate.of(2021, 1, 30));
        outputTask1.setCategory("Todo");

        Task outputTask2 = new Task();
        outputTask2.setId(2);
        outputTask2.setDescription("Buy the thing.");
        outputTask2.setCreateDate(LocalDate.of(2021, 1, 1));
        outputTask2.setDueDate(LocalDate.of(2021, 1, 15));
        outputTask2.setCategory("Grocery List");

        Task outputTask3 = new Task();
        outputTask3.setId(3);
        outputTask3.setDescription("Do the other thing.");
        outputTask3.setCreateDate(LocalDate.of(2021, 2, 10));
        outputTask3.setDueDate(LocalDate.of(2021, 2, 17));
        outputTask3.setCategory("Todo");

        Task taskWithInvalidId = new Task();
        taskWithInvalidId.setId(5);
        taskWithInvalidId.setDescription("Do the other thing.");
        taskWithInvalidId.setCreateDate(LocalDate.of(2021, 2, 10));
        taskWithInvalidId.setDueDate(LocalDate.of(2021, 2, 17));
        taskWithInvalidId.setCategory("Todo");

        List<Task> allTasks = new ArrayList<>();
        allTasks.add(outputTask1);
        allTasks.add(outputTask2);
        allTasks.add(outputTask3);

        List<Task> todoTasks = new ArrayList<>();
        todoTasks.add(outputTask1);
        todoTasks.add(outputTask3);

        List<Task> groceryListTasks = new ArrayList<>();
        groceryListTasks.add(outputTask2);

        doReturn(outputTask1).when(dao).createTask(inputTask1);
        doReturn(outputTask2).when(dao).createTask(inputTask2);
        doReturn(outputTask3).when(dao).createTask(inputTask3);
        doReturn(outputTask1).when(dao).getTask(1);
        doReturn(outputTask2).when(dao).getTask(2);
        doReturn(outputTask3).when(dao).getTask(3);
        doThrow(JdbcOperationFailedException.class).when(dao).getTask(4);
        doThrow(JdbcOperationFailedException.class).when(dao).updateTask(taskWithInvalidId);
        doThrow(JdbcOperationFailedException.class).when(dao).deleteTask(6);
        doReturn(allTasks).when(dao).getAllTasks();
        doReturn(todoTasks).when(dao).getTasksByCategory("Todo");
        doReturn(groceryListTasks).when(dao).getTasksByCategory("Grocery List");
    }

    public void setupMockFeignClient(){
        client = mock(AdServiceFeignClient.class);

        doReturn("Click HERE to qualify for a new car loan!").when(client).getAd();
    }
}