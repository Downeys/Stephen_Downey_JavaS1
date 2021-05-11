package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.SalesTaxRate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class SalesTaxDaoJdbcTemplateImpl implements SalesTaxDao{

    private static final String SELECT_SALES_TAX_BY_STATE =
            "select * from sales_tax_rate where state = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public SalesTaxDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SalesTaxRate findSalesTaxByState(String state) throws JdbcOperationFailedException {
        try{
            return jdbcTemplate.queryForObject(SELECT_SALES_TAX_BY_STATE, this::mapRowToSalesTaxRate, state);
        }catch (EmptyResultDataAccessException e){
            throw new JdbcOperationFailedException("Could not find specified sales tax rate." + state + " is not a valid state code.");
        }
    }

    private SalesTaxRate mapRowToSalesTaxRate(ResultSet rs, int rowNum) throws SQLException{
        SalesTaxRate s = new SalesTaxRate();
        s.setState(rs.getString("state"));
        s.setRate(rs.getBigDecimal("rate"));
        return s;
    }
}
