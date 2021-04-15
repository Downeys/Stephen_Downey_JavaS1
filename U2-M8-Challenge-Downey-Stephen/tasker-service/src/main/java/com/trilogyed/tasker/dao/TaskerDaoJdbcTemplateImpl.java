package com.trilogyed.tasker.dao;

import com.trilogyed.tasker.exceptions.JdbcOperationFailedException;
import com.trilogyed.tasker.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TaskerDaoJdbcTemplateImpl implements TaskerDao {

    public static final String INSERT_TASK =
            "insert into task (task_description, create_date, due_date, category) values (?, ?, ?, ?)";
    public static final String SELECT_TASK_BY_ID =
            "select * from task where task_id = ?";
    public static final String SELECT_ALL_TASKS =
            "select * from task";
    public static final String SELECT_TASKS_BY_CATEGORY =
            "select * from task where category = ?";
    public static final String UPDATE_TASK =
            "update task set task_description = ?, create_date = ?, due_date = ?, category = ? where task_id = ?";
    public static final String DELETE_TASK =
            "delete from task where task_id = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public TaskerDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Task createTask(Task task) {
        jdbcTemplate.update(INSERT_TASK, task.getDescription(), task.getCreateDate(), task.getDueDate(), task.getCategory());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",Integer.class);
        task.setId(id);
        return task;
    }

    @Override
    public Task getTask(int id) throws JdbcOperationFailedException{
        try{
            return jdbcTemplate.queryForObject(SELECT_TASK_BY_ID, this::mapRowToTask, id);
        }catch(DataAccessException e){
            throw new JdbcOperationFailedException("Could not find specified Task. " + id + " is not a valid task id.");
        }
    }

    @Override
    public List<Task> getAllTasks() {
        return jdbcTemplate.query(SELECT_ALL_TASKS, this::mapRowToTask);
    }

    @Override
    public List<Task> getTasksByCategory(String category) {
        return jdbcTemplate.query(SELECT_TASKS_BY_CATEGORY, this::mapRowToTask, category);
    }

    @Override
    public void updateTask(Task task) throws JdbcOperationFailedException {
        int rowsAffected = jdbcTemplate.update(UPDATE_TASK, task.getDescription(), task.getCreateDate(), task.getDueDate(),
                task.getCategory(), task.getId());
        if(rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to update task. " + task.getId() + " is not a valid task id");
        }
    }

    @Override
    public void deleteTask(int id) throws JdbcOperationFailedException{
        int rowsAffected = jdbcTemplate.update(DELETE_TASK, id);
        if(rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to delete task. " + id + " is not a valid task id");
        }
    }

    private Task mapRowToTask(ResultSet rs, int RowNum) throws SQLException{
        Task t = new Task();
        t.setId(rs.getInt("task_id"));
        t.setDescription(rs.getString("task_description"));
        t.setCreateDate(rs.getDate("create_date").toLocalDate());
        t.setDueDate(rs.getDate("due_date").toLocalDate());
        t.setCategory(rs.getString("category"));
        return t;
    }
}
