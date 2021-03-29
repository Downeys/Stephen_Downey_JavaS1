package com.company.U1M5ChallengeDowneyStephen.daos;

import com.company.U1M5ChallengeDowneyStephen.dtos.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PublisherDaoJdbcTemplateImpl implements PublisherDao{

    private static final String INSERT_PUBLISHER_SQL =
            "insert into publisher (name, street, city, state, postal_code, phone, email) " +
                    "values (?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_PUBLISHER_SQL =
            "select * from publisher where publisher_id = ?";
    private static final String SELECT_ALL_PUBLISHERS_SQL =
            "select * from publisher";
    private static final String UPDATE_PUBLISHER_SQL =
            "update publisher set name = ?, street = ?, city = ?, state = ?, postal_code = ?, phone = ?, " +
                    "email = ? where publisher_id = ?";
    private static final String DELETE_PUBLISHER_SQL =
            "delete from publisher where publisher_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public PublisherDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Publisher addPublisher(Publisher publisher) {
        jdbcTemplate.update(INSERT_PUBLISHER_SQL, publisher.getName(), publisher.getStreet(), publisher.getCity(),
                publisher.getState(), publisher.getPostalCode(), publisher.getPhone(), publisher.getEmail());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",Integer.class);
        publisher.setPublisherId(id);
        return publisher;
    }

    @Override
    public Publisher getPublisher(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_PUBLISHER_SQL, this::mapRowToPublisher, id);
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public List<Publisher> getAllPublishers() {
        return jdbcTemplate.query(SELECT_ALL_PUBLISHERS_SQL, this::mapRowToPublisher);
    }

    @Override
    public void updatePublisher(Publisher publisher) {
        jdbcTemplate.update(UPDATE_PUBLISHER_SQL, publisher.getName(), publisher.getStreet(), publisher.getCity(),
                publisher.getState(), publisher.getPostalCode(), publisher.getPhone(), publisher.getEmail(),
                publisher.getPublisherId());
    }

    @Override
    public void deletePublisher(int id) {
        jdbcTemplate.update(DELETE_PUBLISHER_SQL, id);
    }

    private Publisher mapRowToPublisher(ResultSet rs, int rowNum) throws SQLException {
        Publisher p = new Publisher();
        p.setPublisherId(rs.getInt("publisher_id"));
        p.setName(rs.getString("name"));
        p.setStreet(rs.getString("street"));
        p.setCity(rs.getString("city"));
        p.setState(rs.getString("state"));
        p.setPostalCode(rs.getString("postal_code"));
        p.setPhone(rs.getString("phone"));
        p.setEmail(rs.getString("email"));
        return p;
    }
}
