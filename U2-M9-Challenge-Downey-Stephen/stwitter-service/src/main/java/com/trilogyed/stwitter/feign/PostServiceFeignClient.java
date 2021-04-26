package com.trilogyed.stwitter.feign;

import com.trilogyed.stwitter.models.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "post-service")
public interface PostServiceFeignClient {

    @RequestMapping(value = "/posts", method = RequestMethod.POST)
    Post createPost(@RequestBody Post post);

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET)
    Post getPost(@PathVariable int id);

    @RequestMapping(value = "/posts/user/{posterName}", method = RequestMethod.GET)
    List<Post> getPostsByPosterName(@PathVariable String posterName);

}
