package com.trilogyed.post.dao;

import com.trilogyed.post.exceptions.JdbcOperationFailedException;
import com.trilogyed.post.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PostDaoJdbcTemplateImplTest {

    @Autowired
    PostDao dao;

    Post inputPost1, inputPost2, inputPost3;

    @Before
    public void setUp() throws Exception {

        List<Post> posts = dao.getAllPosts();
        for (Post p: posts) {
            dao.deletePost(p.getPostID());
        }

        setupPosts();
    }

    @Test
    public void shouldAddGetDeletePost() throws JdbcOperationFailedException {
        //Act
        inputPost1 = dao.createPost(inputPost1);

        Post postThatWasGotten = dao.getPostById(inputPost1.getPostID());

        //Assert
        assertEquals(inputPost1, postThatWasGotten);

        //Act
        dao.deletePost(inputPost1.getPostID());

        List<Post> posts = dao.getAllPosts();

        //Assert
        assertEquals(0, posts.size());
    }

    @Test
    public void shouldGetPostByPosterName() {
        inputPost1 = dao.createPost(inputPost1);
        inputPost2 = dao.createPost(inputPost2);
        inputPost3 = dao.createPost(inputPost3);

        List<Post> stevePosts = dao.getPostByPosterName("Steve");
        List<Post> davidPosts = dao.getPostByPosterName("David");

        assertEquals(2, stevePosts.size());
        assertEquals(1, davidPosts.size());
    }

    @Test
    public void shouldGetAllPosts() throws JdbcOperationFailedException{
        inputPost1 = dao.createPost(inputPost1);

        //Act
        List<Post> allPostsInDatabase = dao.getAllPosts();

        //Assert
        assertEquals(1, allPostsInDatabase.size());

        inputPost2 = dao.createPost(inputPost2);

        //Act
        allPostsInDatabase = dao.getAllPosts();

        //Assert
        assertEquals(2, allPostsInDatabase.size());


        dao.deletePost(inputPost1.getPostID());

        //Act
        allPostsInDatabase = dao.getAllPosts();

        //Assert
        assertEquals(1, allPostsInDatabase.size());
        assertEquals(inputPost2, allPostsInDatabase.get(0));
    }

    @Test
    public void shouldUpdatePost() throws JdbcOperationFailedException{
        Post expectedOutputFromTest = dao.createPost(inputPost1);
        expectedOutputFromTest.setPost("Come to my show.");

        //Act
        dao.updatePost(expectedOutputFromTest);

        Post actualOutputFromTest = dao.getPostById(inputPost1.getPostID());

        //Assert
        assertEquals(expectedOutputFromTest, actualOutputFromTest);
    }

    @Test
    public void shouldDeletePost() throws JdbcOperationFailedException{
        inputPost1 = dao.createPost(inputPost1);
        inputPost2 = dao.createPost(inputPost2);
        List<Post> postList = dao.getAllPosts();
        assertEquals(2, postList.size());


        //Act
        dao.deletePost(inputPost2.getPostID());
        postList = dao.getAllPosts();

        //Assert
        assertEquals(1, postList.size());
        assertNotEquals(inputPost2, postList.get(0));

        //Act
        dao.deletePost(inputPost1.getPostID());
        postList = dao.getAllPosts();

        //Assert
        assertEquals(0, postList.size());
    }

    private void setupPosts(){
        inputPost1 = new Post();
        inputPost1.setPostDate(LocalDate.of(2021, 4, 19));
        inputPost1.setPosterName("Steve");
        inputPost1.setPost("Look at my food.");

        inputPost2 = new Post();
        inputPost2.setPostDate(LocalDate.of(2021, 3, 9));
        inputPost2.setPosterName("David");
        inputPost2.setPost("I have an opinion.");

        inputPost3 = new Post();
        inputPost3.setPostDate(LocalDate.of(2021, 2, 22));
        inputPost3.setPosterName("Steve");
        inputPost3.setPost("Meme.");
    }
}