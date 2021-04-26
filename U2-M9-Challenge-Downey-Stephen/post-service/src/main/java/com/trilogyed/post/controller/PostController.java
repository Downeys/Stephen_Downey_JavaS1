package com.trilogyed.post.controller;

import com.trilogyed.post.dao.PostDao;
import com.trilogyed.post.exceptions.BadUpdateRequestException;
import com.trilogyed.post.exceptions.JdbcOperationFailedException;
import com.trilogyed.post.model.Post;
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
@CacheConfig(cacheNames = {"posts"})
public class PostController {

    PostDao dao;

    @Autowired
    public PostController(PostDao dao){
        this.dao = dao;
    }

    @CachePut(key = "#post.getPostID()", condition = "#post.getPostID()!=null")
    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Post createPost(@RequestBody @Valid Post post) {
        return dao.createPost(post);
    }

    @Cacheable
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Post getPost(@PathVariable int id) throws JdbcOperationFailedException {
        return dao.getPostById(id);
    }

    @RequestMapping(value = "/posts", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Post> getAllPosts(){
        return dao.getAllPosts();
    }

    @RequestMapping(value = "/posts/user/{posterName}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Post> getPostsByPosterName(@PathVariable String posterName){
        return dao.getPostByPosterName(posterName);
    }

    @CacheEvict(key = "#post.getPostID()")
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updatePost(@RequestBody @Valid Post post, @PathVariable int id)
            throws JdbcOperationFailedException, BadUpdateRequestException{

        if(post.getPostID() == null){
            post.setPostID(id);
        }else if(post.getPostID() != id){
            throw new BadUpdateRequestException("The id you provided (" + id + ") does not match the " +
                    "id of the comment you provided (" + post.getPostID() + ").");
        }

        dao.updatePost(post);
    }

    @CacheEvict
    @RequestMapping(value = "/posts/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deletePost(@PathVariable int id) throws JdbcOperationFailedException{
        dao.deletePost(id);
    }
}
