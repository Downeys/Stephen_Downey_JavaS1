package com.trilogyed.post.dao;

import com.trilogyed.post.exceptions.JdbcOperationFailedException;
import com.trilogyed.post.model.Post;

import java.util.List;

public interface PostDao {
    Post createPost(Post post);
    Post getPostById(int id) throws JdbcOperationFailedException;
    List<Post> getPostByPosterName(String name);
    List<Post> getAllPosts();
    void updatePost(Post post) throws JdbcOperationFailedException;
    void deletePost(int id) throws JdbcOperationFailedException;
}
