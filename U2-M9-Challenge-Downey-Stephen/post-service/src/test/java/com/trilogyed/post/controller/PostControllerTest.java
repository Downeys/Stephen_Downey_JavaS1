package com.trilogyed.post.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.post.dao.PostDao;
import com.trilogyed.post.exceptions.JdbcOperationFailedException;
import com.trilogyed.post.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    PostDao dao;

    Post inputPost1, inputPost2, inputPost3, outputPost1, outputPost2, outputPost3, postWithInvalidId;
    List<Post> allPosts, stevePosts, davidPosts;

    @Before
    public void setUp() throws Exception {
        setupTestPosts();
        setupMockDao();
    }

    @Test
    public void shouldCreatePostAndReturnStatusCreated() throws Exception{
        String inputForTest1 = mapper.writeValueAsString(inputPost1);
        String inputForTest2 = mapper.writeValueAsString(inputPost2);
        String inputForTest3 = mapper.writeValueAsString(inputPost3);

        String expectedOutputForTest1 = mapper.writeValueAsString(outputPost1);
        String expectedOutputForTest2 = mapper.writeValueAsString(outputPost2);
        String expectedOutputForTest3 = mapper.writeValueAsString(outputPost3);

        //Act and Assert
        mockMvc.perform(
                post("/posts")
                        .content(inputForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutputForTest1));

        mockMvc.perform(
                post("/posts")
                        .content(inputForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutputForTest2));

        mockMvc.perform(
                post("/posts")
                        .content(inputForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutputForTest3));
    }

    @Test
    public void shouldGetPostByIdAndStatusOk() throws Exception {
        String test1 = "/posts/1";
        String test2 = "/posts/2";
        String test3 = "/posts/3";

        String expectedOutputForTest1 = mapper.writeValueAsString(outputPost1);
        String expectedOutputForTest2 = mapper.writeValueAsString(outputPost2);
        String expectedOutputForTest3 = mapper.writeValueAsString(outputPost3);

        mockMvc.perform(
                get(test1)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest1));

        mockMvc.perform(
                get(test2)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest2));

        mockMvc.perform(
                get(test3)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputForTest3));
    }

    @Test
    public void shouldGetPostsByPosterNameAndStatusOk() throws Exception{
        String test1 = "/posts/user/Steve";
        String test2 = "/posts/user/David";

        String expectedOutputFromTest1 = mapper.writeValueAsString(stevePosts);
        String expectedOutputFromTest2 = mapper.writeValueAsString(davidPosts);

        mockMvc.perform(
                get(test1)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputFromTest1));

        mockMvc.perform(
                get(test2)
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputFromTest2));
    }

    @Test
    public void shouldGetAllPostsAndStatusOk() throws Exception {
        String expectedOutputFromTest = mapper.writeValueAsString(allPosts);

        mockMvc.perform(
                get("/posts")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputFromTest));
    }

    @Test
    public void shouldReturnStatusOkWhenPassedAValidPostToUpdate() throws Exception{
        String inputForTest1 = mapper.writeValueAsString(outputPost1);
        String inputForTest2 = mapper.writeValueAsString(outputPost2);
        String inputForTest3 = mapper.writeValueAsString(outputPost3);
        String inputForTest4 = mapper.writeValueAsString(inputPost1);
        String inputForTest5 = mapper.writeValueAsString(inputPost2);
        String inputForTest6 = mapper.writeValueAsString(inputPost3);

        //Act and Assert
        mockMvc.perform(
                put("/posts/1")
                        .content(inputForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/posts/2")
                        .content(inputForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/posts/3")
                        .content(inputForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/posts/1")
                        .content(inputForTest4)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/posts/2")
                        .content(inputForTest5)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/posts/3")
                        .content(inputForTest6)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnNoContentWhenPassedAValidIdToDelete() throws Exception {
        //Arrange
        String test1 = "/posts/1";
        String test2 = "/posts/2";
        String test3 = "/posts/3";

        //Act and Assert
        mockMvc.perform(
                delete(test1)
        )
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc.perform(
                delete(test2)
        )
                .andDo(print())
                .andExpect(status().isNoContent());

        mockMvc.perform(
                delete(test3)
        )
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldThrowUnprocessableEntityStatusWhenInvalidPostIsSentToAddOrUpdate() throws Exception{
        Post invalidAddPost1 = new Post();
        invalidAddPost1.setPosterName("Steve");
        invalidAddPost1.setPost("Look at my food.");

        Post invalidAddPost2 = new Post();
        invalidAddPost2.setPostDate(LocalDate.of(2021, 3, 9));
        invalidAddPost2.setPost("I have an opinion.");

        Post invalidUpdatePost1 = new Post();
        invalidUpdatePost1.setPostID(1);
        invalidUpdatePost1.setPosterName("Steve");
        invalidUpdatePost1.setPost("Look at my food.");

        Post invalidUpdatePost2 = new Post();
        invalidUpdatePost2.setPostID(2);
        invalidUpdatePost2.setPostDate(LocalDate.of(2021, 3, 9));
        invalidUpdatePost2.setPost("I have an opinion.");

        String inputForAddTest1 = mapper.writeValueAsString(invalidAddPost1);
        String inputForAddTest2 = mapper.writeValueAsString(invalidAddPost2);
        String inputForUpdateTest1 = mapper.writeValueAsString(invalidUpdatePost1);
        String inputForUpdateTest2 = mapper.writeValueAsString(invalidUpdatePost2);

        mockMvc.perform(
                post("/posts")
                        .content(inputForAddTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/posts")
                        .content(inputForAddTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/posts/1")
                        .content(inputForUpdateTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/posts/2")
                        .content(inputForUpdateTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void shouldReturnStatusNotFoundWhenPassedInvalidIdToUpdateDeleteOrGetByIdMethods() throws Exception{
        //Arrange
        String test1 = "/posts/4";
        String test2 = "/posts/6";

        String inputForTest3 = mapper.writeValueAsString(postWithInvalidId);

        //Act and Assert
        mockMvc.perform(
                get(test1)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(
                delete(test2)
        )
                .andDo(print())
                .andExpect(status().isNotFound());

        mockMvc.perform(
                put("/tasks")
                        .content(inputForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldReturnBadRequestStatusWhenUpdateIsPassedAnIdThatDoesntMatchThePostObjectId() throws Exception{
        String inputForTest1 = mapper.writeValueAsString(outputPost1);
        String inputForTest2 = mapper.writeValueAsString(outputPost2);
        String inputForTest3 = mapper.writeValueAsString(outputPost3);

        //Act and Assert
        mockMvc.perform(
                put("/posts/4")
                        .content(inputForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());

        mockMvc.perform(
                put("/posts/5")
                        .content(inputForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());

        mockMvc.perform(
                put("/posts/6")
                        .content(inputForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private void setupMockDao() throws JdbcOperationFailedException{
        doReturn(outputPost1).when(dao).createPost(inputPost1);
        doReturn(outputPost2).when(dao).createPost(inputPost2);
        doReturn(outputPost3).when(dao).createPost(inputPost3);
        doReturn(outputPost1).when(dao).getPostById(1);
        doReturn(outputPost2).when(dao).getPostById(2);
        doReturn(outputPost3).when(dao).getPostById(3);
        doThrow(JdbcOperationFailedException.class).when(dao).getPostById(4);
        doThrow(JdbcOperationFailedException.class).when(dao).updatePost(postWithInvalidId);
        doThrow(JdbcOperationFailedException.class).when(dao).deletePost(6);
        doReturn(allPosts).when(dao).getAllPosts();
        doReturn(stevePosts).when(dao).getPostByPosterName("Steve");
        doReturn(davidPosts).when(dao).getPostByPosterName("David");
    }

    private void setupTestPosts(){
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

        outputPost1 = new Post();
        outputPost1.setPostID(1);
        outputPost1.setPostDate(LocalDate.of(2021, 4, 19));
        outputPost1.setPosterName("Steve");
        outputPost1.setPost("Look at my food.");

        outputPost2 = new Post();
        outputPost2.setPostID(2);
        outputPost2.setPostDate(LocalDate.of(2021, 3, 9));
        outputPost2.setPosterName("David");
        outputPost2.setPost("I have an opinion.");

        outputPost3 = new Post();
        outputPost3.setPostID(3);
        outputPost3.setPostDate(LocalDate.of(2021, 2, 22));
        outputPost3.setPosterName("Steve");
        outputPost3.setPost("Meme.");

        postWithInvalidId = new Post();
        postWithInvalidId.setPostID(5);
        postWithInvalidId.setPostDate(LocalDate.of(2021, 2, 22));
        postWithInvalidId.setPosterName("Steve");
        postWithInvalidId.setPost("Meme.");

        allPosts = new ArrayList<>();
        allPosts.add(outputPost1);
        allPosts.add(outputPost2);
        allPosts.add(outputPost3);

        stevePosts = new ArrayList<>();
        stevePosts.add(outputPost1);
        stevePosts.add(outputPost3);

        davidPosts = new ArrayList<>();
        davidPosts.add(outputPost2);
    }
}