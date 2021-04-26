package com.trilogyed.commentqueueconsumer.feign;

import com.trilogyed.commentqueueconsumer.models.Comment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "comment-service")
public interface CommentServiceFeignClient {

    @RequestMapping(value = "/comments", method = RequestMethod.POST)
    Comment createComment(@RequestBody Comment comment);

}
