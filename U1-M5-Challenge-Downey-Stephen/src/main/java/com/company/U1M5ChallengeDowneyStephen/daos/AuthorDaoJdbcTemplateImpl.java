package com.company.U1M5ChallengeDowneyStephen.daos;

import com.company.U1M5ChallengeDowneyStephen.dtos.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class AuthorDaoJdbcTemplateImpl implements AuthorDao{

    private static final String INSERT_AUTHOR_SQL =
            "insert into author (first_name, last_name, street, city, state, postal_code, phone, email)" +
                    " values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_AUTHOR_SQL =
            "select * from author where author_id = ?";
    private static final String SELECT_ALL_AUTHORS_SQL =
            "select * from author";
    private static final String UPDATE_AUTHOR_SQL =
            "update author set first_name = ?, last_name = ?, street = ?, city = ?, " +
                    "state = ?, postal_code = ?, phone = ?, email = ? where author_id = ?";
    private static final String DELETE_AUTHOR_SQL =
            "delete from author where author_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public AuthorDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Author addAuthor(Author author) {
        jdbcTemplate.update(INSERT_AUTHOR_SQL, author.getFirstName(), author.getLastName(),
                author.getStreet(), author.getCity(), author.getState(), author.getPostalCode(),
                author.getPhone(), author.getEmail());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()",Integer.class);
        author.setAuthorId(id);
        return author;
    }

    @Override
    public Author getAuthor(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_AUTHOR_SQL, this::mapRowToAuthor, id);
        }catch(DataAccessException e){
            return null;
        }
    }

    @Override
    public List<Author> getAllAuthors() {
        return jdbcTemplate.query(SELECT_ALL_AUTHORS_SQL, this::mapRowToAuthor);
    }

    @Override
    public void updateAuthor(Author author) {
        jdbcTemplate.update(UPDATE_AUTHOR_SQL, author.getFirstName(), author.getLastName(),
                author.getStreet(), author.getCity(), author.getState(), author.getPostalCode(),
                author.getPhone(), author.getEmail(), author.getAuthorId());
    }

    @Override
    public void deleteAuthor(int id) {
        jdbcTemplate.update(DELETE_AUTHOR_SQL, id);
    }

    private Author mapRowToAuthor(ResultSet rs, int rowNum) throws SQLException {
        Author a = new Author();
        a.setAuthorId(rs.getInt("author_id"));
        a.setFirstName(rs.getNString("first_name"));
        a.setLastName(rs.getString("last_name"));
        a.setStreet(rs.getString("street"));
        a.setCity(rs.getString("city"));
        a.setState(rs.getString("state"));
        a.setPostalCode(rs.getString("postal_code"));
        a.setPhone(rs.getString("phone"));
        a.setEmail(rs.getString("email"));
        return a;
    }
}
