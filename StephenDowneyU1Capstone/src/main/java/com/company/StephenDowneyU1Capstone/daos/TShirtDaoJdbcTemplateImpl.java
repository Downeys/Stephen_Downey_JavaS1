package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.TShirt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class TShirtDaoJdbcTemplateImpl implements TShirtDao{

    private static final String INSERT_T_SHIRT_SQL =
            "insert into t_shirt (size, color, description, price, quantity) " +
                    "values (?, ?, ?, ?, ?)";
    private static final String SELECT_T_SHIRT_SQL =
            "select * from t_shirt where t_shirt_id = ?";
    private static final String SELECT_T_SHIRT_BY_COLOR_SQL =
            "select * from t_shirt where color = ?";
    private static final String SELECT_T_SHIRT_BY_SIZE_SQL =
            "select * from t_shirt where size = ?";
    private static final String SELECT_ALL_T_SHIRT_SQL =
            "select * from t_shirt";
    private static final String UPDATE_T_SHIRT_SQL =
            "update t_shirt set size = ?, color = ?, description = ?, price = ?, quantity = ? " +
                    "where t_shirt_id = ?";
    private static final String DELETE_TSHIRT_SQL =
            "delete from t_shirt where t_shirt_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public TShirtDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    @Transactional
    public TShirt saveTShirt(TShirt tShirt) {
        jdbcTemplate.update(INSERT_T_SHIRT_SQL, tShirt.getSize(), tShirt.getColor(), tShirt.getDescription(),
                tShirt.getPrice(), tShirt.getQuantity());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        tShirt.setItemId(id);
        return tShirt;
    }

    @Override
    public TShirt findTShirt(Integer tShirtId) throws JdbcOperationFailedException {
        try{
            return jdbcTemplate.queryForObject(SELECT_T_SHIRT_SQL, this::mapRowToTShirt, tShirtId);
        }catch (EmptyResultDataAccessException e){
            throw new JdbcOperationFailedException("Could not find specified TShirt. " + tShirtId + " is not a valid tShirtId.");
        }
    }

    @Override
    public List<TShirt> findTShirtByColor(String color) {
        return jdbcTemplate.query(SELECT_T_SHIRT_BY_COLOR_SQL, this::mapRowToTShirt, color);
    }

    @Override
    public List<TShirt> findTShirtBySize(String size) {
        return jdbcTemplate.query(SELECT_T_SHIRT_BY_SIZE_SQL, this::mapRowToTShirt, size);
    }

    @Override
    public List<TShirt> findAllTShirts() {
        return jdbcTemplate.query(SELECT_ALL_T_SHIRT_SQL, this::mapRowToTShirt);
    }

    @Override
    public void updateTShirt(TShirt tShirt) throws JdbcOperationFailedException{
        int rowsAffected = jdbcTemplate.update(UPDATE_T_SHIRT_SQL, tShirt.getSize(), tShirt.getColor(), tShirt.getDescription(),
                tShirt.getPrice(), tShirt.getQuantity(), tShirt.getItemId());
        if(rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to update t-shirt" + tShirt.getDescription() + ". " + tShirt.getItemId() + " is not a valid tShirtId");
        }
    }

    @Override
    public void deleteTShirt(Integer tShirtId) throws JdbcOperationFailedException{
        int rowsAffected = jdbcTemplate.update(DELETE_TSHIRT_SQL, tShirtId);
        if(rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to update t-shirt." + tShirtId + " is not a valid tShirtId");
        }
    }

    private TShirt mapRowToTShirt(ResultSet rs, int rowNum) throws SQLException{
        TShirt t = new TShirt();
        t.setItemId(rs.getInt("t_shirt_id"));
        t.setColor(rs.getString("color"));
        t.setSize(rs.getString("size"));
        t.setDescription(rs.getString("description"));
        t.setPrice(rs.getBigDecimal("price"));
        t.setQuantity(rs.getInt("quantity"));
        return t;
    }
}
