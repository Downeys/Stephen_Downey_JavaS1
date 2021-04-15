package com.trilogyed.tasker.dao;

import com.trilogyed.tasker.exceptions.JdbcOperationFailedException;
import com.trilogyed.tasker.model.Task;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TaskerDaoJdbcTemplateImplTest {

    @Autowired
    TaskerDao dao;

    @Before
    public void SetUp() throws JdbcOperationFailedException{
        List<Task> taskList = dao.getAllTasks();
        for (Task t: taskList) {
            dao.deleteTask(t.getId());
        }
    }

    @Test
    public void shouldAddGetDeleteTask() throws JdbcOperationFailedException{
        Task originalTask = new Task();
        originalTask.setDescription("Do the thing.");
        originalTask.setCreateDate(LocalDate.of(2021, 10, 23));
        originalTask.setDueDate(LocalDate.of(2021, 10, 30));
        originalTask.setCategory("Todo");

        //Act
        originalTask = dao.createTask(originalTask);

        Task taskThatWasGotten = dao.getTask(originalTask.getId());

        //Assert
        assertEquals(originalTask, taskThatWasGotten);

        //Act
        dao.deleteTask(originalTask.getId());

        taskThatWasGotten = dao.getTask(originalTask.getId());

        //Assert
        assertNull(taskThatWasGotten);

    }

    @Test
    public void shouldGetAllTasks() throws JdbcOperationFailedException{
        Task inputTask1 = new Task();
        inputTask1.setDescription("Do the thing.");
        inputTask1.setCreateDate(LocalDate.of(2021, 10, 23));
        inputTask1.setDueDate(LocalDate.of(2021, 10, 30));
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

        inputTask1 = dao.createTask(inputTask1);

        //Act
        List<Task> allTasksInDatabase = dao.getAllTasks();

        //Assert
        assertEquals(1, allTasksInDatabase.size());

        inputTask2 = dao.createTask(inputTask2);

        //Act
        allTasksInDatabase = dao.getAllTasks();

        //Assert
        assertEquals(2, allTasksInDatabase.size());

        inputTask3 = dao.createTask(inputTask3);

        //Act
        allTasksInDatabase = dao.getAllTasks();

        //Assert
        assertEquals(3, allTasksInDatabase.size());

        dao.deleteTask(inputTask1.getId());
        dao.deleteTask(inputTask3.getId());

        //Act
        allTasksInDatabase = dao.getAllTasks();

        //Assert
        assertEquals(1, allTasksInDatabase.size());
        assertEquals(inputTask2, allTasksInDatabase.get(0));
    }

    @Test
    public void shouldGetTasksByCategory() {
        Task inputTask1 = new Task();
        inputTask1.setDescription("Do the thing.");
        inputTask1.setCreateDate(LocalDate.of(2021, 10, 23));
        inputTask1.setDueDate(LocalDate.of(2021, 10, 30));
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

        inputTask1 = dao.createTask(inputTask1);
        inputTask2 = dao.createTask(inputTask2);
        inputTask3 = dao.createTask(inputTask3);

        //Act
        List<Task> resultFromTest1 = dao.getTasksByCategory("Grocery List");
        List<Task> resultFromTest2 = dao.getTasksByCategory("Todo");

        //Assert
        assertEquals(1, resultFromTest1.size());
        assertEquals("Grocery List", resultFromTest1.get(0).getCategory());

        assertEquals(2, resultFromTest2.size());
        assertEquals("Todo", resultFromTest2.get(0).getCategory());
        assertEquals("Todo", resultFromTest2.get(1).getCategory());
    }

    @Test
    public void shouldUpdateTask() throws JdbcOperationFailedException {

        Task inputTask1 = new Task();
        inputTask1.setDescription("Do the thing.");
        inputTask1.setCreateDate(LocalDate.of(2021, 10, 23));
        inputTask1.setDueDate(LocalDate.of(2021, 10, 30));
        inputTask1.setCategory("Todo");

        Task expectedOutputFromTest = dao.createTask(inputTask1);
        expectedOutputFromTest.setDescription("Bananas");
        expectedOutputFromTest.setCategory("Grocery List");

        //Act
        dao.updateTask(expectedOutputFromTest);

        Task actualOutPutFromTest = dao.getTask(inputTask1.getId());

        //Assert
        assertEquals(expectedOutputFromTest, actualOutPutFromTest);
    }

    @Test
    public void shouldDeleteTask() throws JdbcOperationFailedException{
        Task inputTask1 = new Task();
        inputTask1.setDescription("Do the thing.");
        inputTask1.setCreateDate(LocalDate.of(2021, 10, 23));
        inputTask1.setDueDate(LocalDate.of(2021, 10, 30));
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

        inputTask1 = dao.createTask(inputTask1);
        inputTask2 = dao.createTask(inputTask2);
        inputTask3 = dao.createTask(inputTask3);

        //Act
        dao.deleteTask(inputTask2.getId());
        List<Task> taskList = dao.getAllTasks();

        //Assert
        assertEquals(2, taskList.size());
        assertNotEquals(inputTask2, taskList.get(0));
        assertNotEquals(inputTask2, taskList.get(1));

        //Act
        dao.deleteTask(inputTask1.getId());
        taskList = dao.getAllTasks();

        //Assert
        assertEquals(1, taskList.size());
        assertNotEquals(inputTask1, taskList.get(0));

        //Act
        dao.deleteTask(inputTask3.getId());
        taskList = dao.getAllTasks();

        //Assert
        assertEquals(0, taskList.size());

    }
}