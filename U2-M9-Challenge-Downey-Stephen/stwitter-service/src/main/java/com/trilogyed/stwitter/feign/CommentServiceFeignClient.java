package com.trilogyed.stwitter.feign;

import com.trilogyed.stwitter.models.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "comment-service")
public interface CommentServiceFeignClient {

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    Comment createComment(@RequestBody Comment comment);

    @RequestMapping(value = "/comments/post/{postId}", method = RequestMethod.GET)
    List<Comment> getCommentsByPostId(@PathVariable Integer postId);

}
