package com.trilogyed.tasker.controller;

import com.trilogyed.tasker.exceptions.JdbcOperationFailedException;
import com.trilogyed.tasker.model.TaskViewModel;
import com.trilogyed.tasker.service.TaskerServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TaskerController {

    TaskerServiceLayer service;

    @Autowired
    public TaskerController(TaskerServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public TaskViewModel createTask(@RequestBody @Valid TaskViewModel task){
        return service.createNewTask(task);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateTask(@RequestBody @Valid TaskViewModel task) throws JdbcOperationFailedException{
        service.updateTask(task);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public TaskViewModel getTaskById(@PathVariable Integer id) throws JdbcOperationFailedException {
        return service.fetchTask(id);
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<TaskViewModel> getAllTasks(){
        return service.fetchAllTasks();
    }

    @RequestMapping(value = "/tasks/category/{category}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<TaskViewModel> getTasksByCategory(@PathVariable String category){
        return service.fetchTasksByCategory(category);
    }

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTask(@PathVariable int id) throws JdbcOperationFailedException{
        service.deleteTask(id);
    }
}
