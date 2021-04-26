package com.trilogyed.post.dao;

import com.trilogyed.post.exceptions.JdbcOperationFailedException;
import com.trilogyed.post.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PostDaoJdbcTemplateImpl implements PostDao{

    private static final String INSERT_POST_SQL =
            "insert into post (poster_name, post_date, post) values (?, ?, ?)";
    private static final String SELECT_POST_SQL =
            "select * from post where post_id = ?";
    private static final String SELECT_POST_BY_POSTER_NAME =
            "select * from post where poster_name = ?";
    private static final String SELECT_ALL_POSTS_SQL =
            "select * from post";
    private static final String UPDATE_POST_SQL =
            "update post set poster_name = ?, post_date = ?, post = ? where post_id = ?";
    private static final String DELETE_POST_SQL =
            "delete from post where post_id = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public PostDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Post createPost(Post post) {
        jdbcTemplate.update(INSERT_POST_SQL, post.getPosterName(), post.getPostDate(), post.getPost());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        post.setPostID(id);
        return post;
    }

    @Override
    public Post getPostById(int id) throws JdbcOperationFailedException{
        try{
            return jdbcTemplate.queryForObject(SELECT_POST_SQL, this::mapRowToPost, id);
        }catch (EmptyResultDataAccessException e){
            throw new JdbcOperationFailedException("Could not find specified post." + id + " is not a valid post id.");
        }
    }

    @Override
    public List<Post> getPostByPosterName(String name) {
        return jdbcTemplate.query(SELECT_POST_BY_POSTER_NAME, this::mapRowToPost, name);
    }

    @Override
    public List<Post> getAllPosts() {
        return jdbcTemplate.query(SELECT_ALL_POSTS_SQL, this::mapRowToPost);
    }

    @Override
    public void updatePost(Post post) throws JdbcOperationFailedException{
        int rowsAffected = jdbcTemplate.update(UPDATE_POST_SQL, post.getPosterName(), post.getPostDate(), post.getPost(), post.getPostID());
        if (rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to update post " + post.getPostID() + ". " + post + " was not found in system.");
        }
    }

    @Override
    public void deletePost(int id) throws JdbcOperationFailedException{
        int rowsAffected = jdbcTemplate.update(DELETE_POST_SQL, id);
        if (rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to delete post. " + id + " is an invalid post id");
        }
    }

    private Post mapRowToPost(ResultSet rs, int rowNum) throws SQLException {
        Post p = new Post();
        p.setPostID(rs.getInt("post_id"));
        p.setPosterName(rs.getString("poster_name"));
        p.setPostDate(rs.getDate("post_date").toLocalDate());
        p.setPost(rs.getString("post"));
        return p;
    }
}
