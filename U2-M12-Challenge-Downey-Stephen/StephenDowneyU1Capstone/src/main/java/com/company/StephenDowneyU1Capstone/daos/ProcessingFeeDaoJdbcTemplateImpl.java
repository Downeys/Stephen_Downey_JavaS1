package com.company.StephenDowneyU1Capstone.daos;

import com.company.StephenDowneyU1Capstone.exceptions.JdbcOperationFailedException;
import com.company.StephenDowneyU1Capstone.models.ProcessingFee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class ProcessingFeeDaoJdbcTemplateImpl implements ProcessingFeeDao{

    private static final String SELECT_PROCESSING_FEE_BY_PRODUCT_TYPE = "select * from processing_fee " +
            "where product_type = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProcessingFeeDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ProcessingFee findProcessingFeeByItemType(String itemType) throws JdbcOperationFailedException {
        try{
            return jdbcTemplate.queryForObject(SELECT_PROCESSING_FEE_BY_PRODUCT_TYPE, this::mapRowToProcessingFee, itemType);
        }catch (EmptyResultDataAccessException e){
            throw new JdbcOperationFailedException("Could not find specified processing fee." + itemType + " is not a valid itemType.");
        }
    }

    private ProcessingFee mapRowToProcessingFee(ResultSet rs, int rowNum) throws SQLException {
        ProcessingFee p = new ProcessingFee();
        p.setProductType(rs.getString("product_type"));
        p.setFee(rs.getBigDecimal("fee"));
        return p;
    }
}
