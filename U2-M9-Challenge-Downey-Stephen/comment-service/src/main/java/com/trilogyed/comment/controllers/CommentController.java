package com.trilogyed.comment.controllers;

import com.trilogyed.comment.dao.CommentDao;
import com.trilogyed.comment.exceptions.BadUpdateRequestException;
import com.trilogyed.comment.exceptions.JdbcOperationFailedException;
import com.trilogyed.comment.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@CacheConfig(cacheNames = {"comments"})
public class CommentController {

    CommentDao dao;

    @Autowired
    public CommentController(CommentDao dao){
        this.dao = dao;
    }

    @CachePut(key = "#comment.getCommentId()", condition = "#comment.getCommentId()!=null")
    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Comment createComment(@RequestBody @Valid Comment comment) {
        return dao.createComment(comment);
    }

    @Cacheable
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Comment getComment(@PathVariable int id) throws JdbcOperationFailedException{
        return dao.getComment(id);
    }

    @RequestMapping(value = "/comments", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Comment> getAllComments(){
        return dao.getAllComments();
    }

    @RequestMapping(value = "/comments/post/{postId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Comment> getCommentsByPostId(@PathVariable Integer postId){
        return dao.getCommentByPostId(postId);
    }

    @CacheEvict(key = "#comment.getCommentId()")
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateComment(@RequestBody @Valid Comment comment, @PathVariable int id)
            throws JdbcOperationFailedException, BadUpdateRequestException {

        if(comment.getCommentId() == null){
            comment.setCommentId(id);
        }else if(comment.getCommentId() != id){
            throw new BadUpdateRequestException("The id you provided (" + id + ") does not match the " +
                    "id of the comment you provided (" + comment.getCommentId() + ").");
        }

        dao.updateComment(comment);
    }

    @CacheEvict
    @RequestMapping(value = "/comments/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteComment(@PathVariable int id) throws JdbcOperationFailedException{
        dao.deleteComment(id);
    }
}
