package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.Console;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ConsoleDaoJdbcTemplateImpl implements ConsoleDao{

    private static final String INSERT_CONSOLE_SQL =
            "insert into console (model, manufacturer, memory_amount, processor, price, quantity) " +
                    "values (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_CONSOLE_SQL =
            "select * from console where console_id = ?";
    private static final String SELECT_CONSOLE_BY_MANUFACTURER_SQL =
            "select * from console where manufacturer = ?";
    private static final String SELECT_ALL_CONSOLES_SQL =
            "select * from console";
    private static final String UPDATE_CONSOLE_SQL =
            "update console set model = ?, manufacturer = ?, memory_amount = ?, processor = ?, price = ?, quantity = ? " +
                    "where console_id = ?";
    private static final String DELETE_CONSOLE_SQL =
            "delete from console where console_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ConsoleDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Console saveConsole(Console console) {
        jdbcTemplate.update(INSERT_CONSOLE_SQL, console.getModel(), console.getManufacturer(), console.getMemoryAmount(),
                console.getProcessor(), console.getPrice(), console.getQuantity());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        console.setItemId(id);
        return console;
    }

    @Override
    public Console findConsole(Integer consoleId) throws JdbcOperationFailedException {
        try{
            return jdbcTemplate.queryForObject(SELECT_CONSOLE_SQL, this::mapRowToConsole, consoleId);
        }catch (EmptyResultDataAccessException e){
            throw new JdbcOperationFailedException("Could not find specified console." + consoleId + " is not a valid consoleId.");
        }
    }

    @Override
    public List<Console> findConsoleByManufacturer(String manufacturer) {
        return jdbcTemplate.query(SELECT_CONSOLE_BY_MANUFACTURER_SQL, this::mapRowToConsole, manufacturer);
    }

    @Override
    public List<Console> findAllConsoles() {
        return jdbcTemplate.query(SELECT_ALL_CONSOLES_SQL, this::mapRowToConsole);
    }

    @Override
    public void updateConsole(Console console) throws JdbcOperationFailedException {
        int rowsAffected = jdbcTemplate.update(UPDATE_CONSOLE_SQL, console.getModel(), console.getManufacturer(), console.getMemoryAmount(),
                console.getProcessor(), console.getPrice(), console.getQuantity(), console.getItemId());
        if (rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to update " + console.getModel() + ". " + console + " was not found in system.");
        }
    }

    @Override
    public void deleteConsole(Integer consoleId) throws JdbcOperationFailedException{
        int rowsAffected = jdbcTemplate.update(DELETE_CONSOLE_SQL, consoleId);
        if (rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to delete console. " + consoleId + " is an invalid itemId");
        }
    }

    private Console mapRowToConsole(ResultSet rs, int rowNum) throws SQLException {
        Console c = new Console();
        c.setItemId(rs.getInt("console_id"));
        c.setModel(rs.getString("model"));
        c.setManufacturer(rs.getString("manufacturer"));
        c.setMemoryAmount(rs.getString("memory_amount"));
        c.setProcessor(rs.getString("processor"));
        c.setPrice(rs.getBigDecimal("price"));
        c.setQuantity(rs.getInt("quantity"));
        return c;
    }
}
