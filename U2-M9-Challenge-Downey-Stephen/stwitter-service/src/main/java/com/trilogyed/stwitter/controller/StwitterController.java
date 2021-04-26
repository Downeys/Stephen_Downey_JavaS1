package com.trilogyed.stwitter.controller;

import com.trilogyed.stwitter.models.PostViewModel;
import com.trilogyed.stwitter.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class StwitterController {

    ServiceLayer service;

    @Autowired
    public StwitterController(ServiceLayer service) {
        this.service = service;
    }

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public PostViewModel savePost(@RequestBody @Valid PostViewModel postViewModel){
        return service.createPost(postViewModel);
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public PostViewModel getPost(@PathVariable Integer id){
        return service.getPostById(id);
    }

    @RequestMapping(value = "posts/user/{posterName}",method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<PostViewModel> getPostsByPosterName(@PathVariable String posterName){
        return service.getPostByPosterName(posterName);
    }
}
