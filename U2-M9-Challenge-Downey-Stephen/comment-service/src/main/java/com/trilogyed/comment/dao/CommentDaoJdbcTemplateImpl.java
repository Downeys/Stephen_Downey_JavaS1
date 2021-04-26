package com.trilogyed.comment.dao;

import com.trilogyed.comment.exceptions.JdbcOperationFailedException;
import com.trilogyed.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CommentDaoJdbcTemplateImpl implements CommentDao{

    private static final String INSERT_COMMENT_SQL =
            "insert into comment (post_id, create_date, commenter_name, comment) values (?, ?, ?, ?)";
    private static final String SELECT_COMMENT_SQL =
            "select * from comment where comment_id = ?";
    private static final String SELECT_COMMENT_BY_POST_ID =
            "select * from comment where post_id = ?";
    private static final String SELECT_ALL_COMMENTS_SQL =
            "select * from comment";
    private static final String UPDATE_COMMENT_SQL =
            "update comment set post_id = ?, create_date = ?, commenter_name = ?, comment = ? where comment_id = ?";
    private static final String DELETE_COMMENT_SQL =
            "delete from comment where comment_id = ?";

    JdbcTemplate jdbcTemplate;

    @Autowired
    public CommentDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Comment createComment(Comment comment) {
        jdbcTemplate.update(INSERT_COMMENT_SQL, comment.getPostId(), comment.getCreateDate(), comment.getCommenterName(), comment.getComment());
        int id = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);
        comment.setCommentId(id);
        return comment;
    }

    @Override
    public Comment getComment(int commentId) throws JdbcOperationFailedException{
        try{
            return jdbcTemplate.queryForObject(SELECT_COMMENT_SQL, this::mapRowToComment, commentId);
        }catch (EmptyResultDataAccessException e){
            throw new JdbcOperationFailedException("Could not find specified comment." + commentId + " is not a valid comment id.");
        }
    }

    @Override
    public List<Comment> getCommentByPostId(int postId) {
        return jdbcTemplate.query(SELECT_COMMENT_BY_POST_ID, this::mapRowToComment, postId);
    }

    @Override
    public List<Comment> getAllComments() {
        return jdbcTemplate.query(SELECT_ALL_COMMENTS_SQL, this::mapRowToComment);
    }

    @Override
    public void updateComment(Comment comment) throws JdbcOperationFailedException{
        int rowsAffected = jdbcTemplate.update(UPDATE_COMMENT_SQL, comment.getPostId(), comment.getCreateDate(),
                comment.getCommenterName(), comment.getComment(), comment.getCommentId());
        if (rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to update comment. " + comment.getCommentId() + " is an invalid comment id.");
        }
    }

    @Override
    public void deleteComment(int commentId) throws JdbcOperationFailedException{
        int rowsAffected = jdbcTemplate.update(DELETE_COMMENT_SQL, commentId);
        if (rowsAffected == 0){
            throw new JdbcOperationFailedException("Unable to delete comment. " + commentId + " is an invalid comment id");
        }
    }

    private Comment mapRowToComment(ResultSet rs, int rowNum) throws SQLException {
        Comment c = new Comment();
        c.setCommentId(rs.getInt("comment_id"));
        c.setPostId(rs.getInt("post_id"));
        c.setCommenterName(rs.getString("commenter_name"));
        c.setCreateDate(rs.getDate("create_date").toLocalDate());
        c.setComment(rs.getString("comment"));
        return c;
    }
}
