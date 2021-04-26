package com.trilogyed.comment.dao;

import com.trilogyed.comment.exceptions.JdbcOperationFailedException;
import com.trilogyed.comment.model.Comment;

import java.util.List;

public interface CommentDao {
    Comment createComment(Comment comment);
    Comment getComment(int commentId) throws JdbcOperationFailedException;
    List<Comment> getCommentByPostId(int postId);
    List<Comment> getAllComments();
    void updateComment(Comment comment) throws JdbcOperationFailedException;
    void deleteComment(int commentId) throws JdbcOperationFailedException;
}
