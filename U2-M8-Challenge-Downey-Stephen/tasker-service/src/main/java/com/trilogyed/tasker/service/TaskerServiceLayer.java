package com.trilogyed.tasker.service;

import com.trilogyed.tasker.dao.TaskerDao;
import com.trilogyed.tasker.exceptions.JdbcOperationFailedException;
import com.trilogyed.tasker.feign.AdServiceFeignClient;
import com.trilogyed.tasker.model.Task;
import com.trilogyed.tasker.model.TaskViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskerServiceLayer {

    TaskerDao dao;
    AdServiceFeignClient client;

    @Autowired
    public TaskerServiceLayer(TaskerDao dao, AdServiceFeignClient client){
        this.dao = dao;
        this.client = client;
    }

    public TaskViewModel fetchTask(int id) throws JdbcOperationFailedException {

        Task task = dao.getTask(id);
        TaskViewModel tvm = mapTaskToTaskViewModel(task);
        tvm = attachAdToViewModel(tvm);

        return tvm;
    }

    public List<TaskViewModel> fetchAllTasks() {
        List<Task> allTasks = dao.getAllTasks();
        List<TaskViewModel> allTaskViewModels = new ArrayList<>();
        for (Task t: allTasks) {
            allTaskViewModels.add(mapTaskToTaskViewModel(t));
        }
        for (TaskViewModel t: allTaskViewModels) {
            t = attachAdToViewModel(t);
        }
        return allTaskViewModels;
    }

    public List<TaskViewModel> fetchTasksByCategory(String category) {
        List<Task> tasks = dao.getTasksByCategory(category);
        List<TaskViewModel> taskModels = new ArrayList<>();
        for (Task t: tasks) {
            taskModels.add(mapTaskToTaskViewModel(t));
        }
        for (TaskViewModel t: taskModels) {
            t = attachAdToViewModel(t);
        }
        return taskModels;
    }

    @Transactional
    public TaskViewModel createNewTask(TaskViewModel taskViewModel) {

        Task task = new Task();
        task.setDescription(taskViewModel.getDescription());
        task.setCreateDate(taskViewModel.getCreateDate());
        task.setDueDate(taskViewModel.getDueDate());
        task.setCategory(taskViewModel.getCategory());

        task = dao.createTask(task);
        taskViewModel.setId(task.getId());

        taskViewModel = attachAdToViewModel(taskViewModel);

        return taskViewModel;
    }

    public void deleteTask(int id) throws JdbcOperationFailedException {
        dao.deleteTask(id);
    }

    public void updateTask(TaskViewModel taskViewModel) throws JdbcOperationFailedException{
        Task task = new Task();
        task.setId(taskViewModel.getId());
        task.setDescription(taskViewModel.getDescription());
        task.setCreateDate(taskViewModel.getCreateDate());
        task.setDueDate(taskViewModel.getDueDate());
        task.setCategory(taskViewModel.getCategory());
        dao.updateTask(task);
    }

    public TaskViewModel mapTaskToTaskViewModel(Task task){
        TaskViewModel tvm = new TaskViewModel();

        tvm.setId(task.getId());
        tvm.setDescription(task.getDescription());
        tvm.setCreateDate(task.getCreateDate());
        tvm.setDueDate(task.getDueDate());
        tvm.setCategory(task.getCategory());
        return tvm;
    }

    public TaskViewModel attachAdToViewModel(TaskViewModel tvm){
        String ad = client.getAd();
        tvm.setAdvertisement(ad);
        return tvm;
    }

}
