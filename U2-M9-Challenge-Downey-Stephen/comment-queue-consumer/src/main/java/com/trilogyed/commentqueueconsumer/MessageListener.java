package com.trilogyed.commentqueueconsumer;

import com.trilogyed.commentqueueconsumer.feign.CommentServiceFeignClient;
import com.trilogyed.commentqueueconsumer.models.Comment;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageListener {

    CommentServiceFeignClient commentClient;

    @Autowired
    public MessageListener(CommentServiceFeignClient commentClient){
        this.commentClient = commentClient;
    }

    @RabbitListener(queues = CommentQueueConsumerApplication.QUEUE_NAME)
    public void receiveMessage(Comment msg) {
        commentClient.createComment(msg);
        System.out.println(msg.toString());
    }

}
