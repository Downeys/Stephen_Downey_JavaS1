package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.feign.CommentServiceFeignClient;
import com.trilogyed.stwitter.feign.PostServiceFeignClient;
import com.trilogyed.stwitter.models.Comment;
import com.trilogyed.stwitter.models.Post;
import com.trilogyed.stwitter.models.PostViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceLayer {
    public static final String EXCHANGE = "comment-exchange";
    public static final String ROUTING_KEY = "comment.create.account.controller";

    private RabbitTemplate rabbitTemplate;
    private final CommentServiceFeignClient commentClient;
    private final PostServiceFeignClient postClient;

    @Autowired
    public ServiceLayer(CommentServiceFeignClient commentClient, PostServiceFeignClient postClient,
                        RabbitTemplate rabbitTemplate){
        this.commentClient = commentClient;
        this.postClient = postClient;
        this.rabbitTemplate = rabbitTemplate;
    }

    @Transactional
    public PostViewModel createPost(PostViewModel postViewModel){
        Post post = savePost(postViewModel);
        saveComments(postViewModel.getComments(), post.getPostID());
        return buildPostViewModel(post, new ArrayList<>());
    }

    public PostViewModel getPostById(int id){
        Post post = postClient.getPost(id);
        List<Comment> comments = commentClient.getCommentsByPostId(id);
        return buildPostViewModel(post, comments);
    }

    public List<PostViewModel> getPostByPosterName(String posterName){
        List<PostViewModel> returnVal = new ArrayList<>();

        List<Post> posts = postClient.getPostsByPosterName(posterName);
        for (Post p: posts) {
            List<Comment> comments = commentClient.getCommentsByPostId(p.getPostID());
            returnVal.add(buildPostViewModel(p, comments));
        }

        return returnVal;
    }

    private PostViewModel buildPostViewModel(Post post, List<Comment> comments){
        PostViewModel returnVal = new PostViewModel();
        returnVal.setPostId(post.getPostID());
        returnVal.setPostDate(post.getPostDate());
        returnVal.setPosterName(post.getPosterName());
        returnVal.setPostContent(post.getPost());
        returnVal.setComments(comments);
        return returnVal;
    }

    private Post savePost(PostViewModel postViewModel){
        Post post = new Post();
        post.setPostDate(postViewModel.getPostDate());
        post.setPosterName(postViewModel.getPosterName());
        post.setPost(postViewModel.getPostContent());
        Post newPost  = postClient.createPost(post);
        return newPost;
    }

    private void saveComments(List<Comment> comments, Integer postId){

        for (Comment comment: comments) {
            comment.setPostId(postId);
            System.out.println("Sending message...");
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, comment);
            System.out.println("Message Sent");
        }
    }
}
