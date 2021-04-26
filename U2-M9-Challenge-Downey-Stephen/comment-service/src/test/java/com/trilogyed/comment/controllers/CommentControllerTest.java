package com.trilogyed.comment.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.comment.dao.CommentDao;
import com.trilogyed.comment.exceptions.JdbcOperationFailedException;
import com.trilogyed.comment.model.Comment;
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

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class CommentControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    CommentDao dao;

    Comment inputComment1, inputComment2, inputComment3, outputComment1, outputComment2, outputComment3, commentWithInvalidId;
    List<Comment> allComments, post1Comments, post2Comments;

    @Before
    public void setUp() throws Exception {
        setupTestComments();
        setupMockDao();
    }

    @Test
    public void shouldCreateCommentAndReturnStatusCreated() throws Exception{
        String inputForTest1 = mapper.writeValueAsString(inputComment1);
        String inputForTest2 = mapper.writeValueAsString(inputComment2);
        String inputForTest3 = mapper.writeValueAsString(inputComment3);

        String expectedOutputForTest1 = mapper.writeValueAsString(outputComment1);
        String expectedOutputForTest2 = mapper.writeValueAsString(outputComment2);
        String expectedOutputForTest3 = mapper.writeValueAsString(outputComment3);

        //Act and Assert
        mockMvc.perform(
                post("/comments")
                        .content(inputForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutputForTest1));

        mockMvc.perform(
                post("/comments")
                        .content(inputForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutputForTest2));

        mockMvc.perform(
                post("/comments")
                        .content(inputForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(expectedOutputForTest3));
    }

    @Test
    public void shouldGetCommentAndStatusOk() throws Exception {
        String test1 = "/comments/1";
        String test2 = "/comments/2";
        String test3 = "/comments/3";

        String expectedOutputForTest1 = mapper.writeValueAsString(outputComment1);
        String expectedOutputForTest2 = mapper.writeValueAsString(outputComment2);
        String expectedOutputForTest3 = mapper.writeValueAsString(outputComment3);

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
    public void shouldGetAllCommentsAndStatusOk() throws Exception{
        String expectedOutputFromTest = mapper.writeValueAsString(allComments);

        mockMvc.perform(
                get("/comments")
        )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(expectedOutputFromTest));
    }

    @Test
    public void shouldGetCommentsByPostIdAndStatusOk() throws Exception{
        String test1 = "/comments/post/1";
        String test2 = "/comments/post/2";

        String expectedOutputFromTest1 = mapper.writeValueAsString(post1Comments);
        String expectedOutputFromTest2 = mapper.writeValueAsString(post2Comments);

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
    public void shouldReturnOkStatusWhenPassedValidCommentToUpdate() throws Exception{
        String inputForTest1 = mapper.writeValueAsString(outputComment1);
        String inputForTest2 = mapper.writeValueAsString(outputComment2);
        String inputForTest3 = mapper.writeValueAsString(outputComment3);
        String inputForTest4 = mapper.writeValueAsString(inputComment1);
        String inputForTest5 = mapper.writeValueAsString(inputComment2);
        String inputForTest6 = mapper.writeValueAsString(inputComment3);

        //Act and Assert
        mockMvc.perform(
                put("/comments/1")
                        .content(inputForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/comments/2")
                        .content(inputForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/comments/3")
                        .content(inputForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/comments/1")
                        .content(inputForTest4)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/comments/2")
                        .content(inputForTest5)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());

        mockMvc.perform(
                put("/comments/3")
                        .content(inputForTest6)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnStatusNoContentWhenPassedValidIdToDelete() throws Exception{
        //Arrange
        String test1 = "/comments/1";
        String test2 = "/comments/2";
        String test3 = "/comments/3";

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
        Comment invalidAddComment1 = new Comment();
        invalidAddComment1.setCommenterName("Jerry");
        invalidAddComment1.setCreateDate(LocalDate.of(2021, 4, 19));
        invalidAddComment1.setComment("Yummy");

        Comment invalidAddComment2 = new Comment();
        invalidAddComment2.setPostId(2);
        invalidAddComment2.setCreateDate(LocalDate.of(2021, 4, 18));
        invalidAddComment2.setComment("I disagree");

        Comment invalidAddComment3 = new Comment();
        invalidAddComment3.setPostId(1);
        invalidAddComment3.setCommenterName("Gerald");
        invalidAddComment3.setComment("That is sooooo interesting.");

        Comment invalidUpdateComment1 = new Comment();
        invalidUpdateComment1.setCommentId(1);
        invalidUpdateComment1.setCommenterName("Jerry");
        invalidUpdateComment1.setCreateDate(LocalDate.of(2021, 4, 19));
        invalidUpdateComment1.setComment("Yummy");

        Comment invalidUpdateComment2 = new Comment();
        invalidUpdateComment2.setCommentId(2);
        invalidUpdateComment2.setPostId(2);
        invalidUpdateComment2.setCreateDate(LocalDate.of(2021, 4, 18));
        invalidUpdateComment2.setComment("I disagree");

        Comment invalidUpdateComment3 = new Comment();
        invalidUpdateComment3.setCommentId(3);
        invalidUpdateComment3.setPostId(1);
        invalidUpdateComment3.setCommenterName("Gerald");
        invalidUpdateComment3.setComment("That is sooooo interesting.");

        String inputForAddTest1 = mapper.writeValueAsString(invalidAddComment1);
        String inputForAddTest2 = mapper.writeValueAsString(invalidAddComment2);
        String inputForAddTest3 = mapper.writeValueAsString(invalidAddComment3);
        String inputForUpdateTest1 = mapper.writeValueAsString(invalidUpdateComment1);
        String inputForUpdateTest2 = mapper.writeValueAsString(invalidUpdateComment2);
        String inputForUpdateTest3 = mapper.writeValueAsString(invalidUpdateComment3);

        mockMvc.perform(
                post("/comments")
                        .content(inputForAddTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/comments")
                        .content(inputForAddTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/comments")
                        .content(inputForAddTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/comments/1")
                        .content(inputForUpdateTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/comments/2")
                        .content(inputForUpdateTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                put("/comments/3")
                        .content(inputForUpdateTest3)
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

        String inputForTest3 = mapper.writeValueAsString(commentWithInvalidId);

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
    public void shouldReturnBadRequestStatusWhenUpdateIsPassedAnIdPathVariableThatDoesntMatchTheCommentObjectId() throws Exception{
        String inputForTest1 = mapper.writeValueAsString(outputComment1);
        String inputForTest2 = mapper.writeValueAsString(outputComment2);
        String inputForTest3 = mapper.writeValueAsString(outputComment3);

        //Act and Assert
        mockMvc.perform(
                put("/comments/6")
                        .content(inputForTest1)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());

        mockMvc.perform(
                put("/comments/7")
                        .content(inputForTest2)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());

        mockMvc.perform(
                put("/comments/8")
                        .content(inputForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    private void setupMockDao() throws JdbcOperationFailedException{
        doReturn(outputComment1).when(dao).createComment(inputComment1);
        doReturn(outputComment2).when(dao).createComment(inputComment2);
        doReturn(outputComment3).when(dao).createComment(inputComment3);
        doReturn(outputComment1).when(dao).getComment(1);
        doReturn(outputComment2).when(dao).getComment(2);
        doReturn(outputComment3).when(dao).getComment(3);
        doThrow(JdbcOperationFailedException.class).when(dao).getComment(4);
        doThrow(JdbcOperationFailedException.class).when(dao).updateComment(commentWithInvalidId);
        doThrow(JdbcOperationFailedException.class).when(dao).deleteComment(6);
        doReturn(allComments).when(dao).getAllComments();
        doReturn(post1Comments).when(dao).getCommentByPostId(1);
        doReturn(post2Comments).when(dao).getCommentByPostId(2);
    }

    private void setupTestComments(){
        inputComment1 = new Comment();
        inputComment1.setPostId(1);
        inputComment1.setCommenterName("Jerry");
        inputComment1.setCreateDate(LocalDate.of(2021, 4, 19));
        inputComment1.setComment("Yummy");

        inputComment2 = new Comment();
        inputComment2.setPostId(2);
        inputComment2.setCommenterName("Gerald");
        inputComment2.setCreateDate(LocalDate.of(2021, 4, 18));
        inputComment2.setComment("I disagree");

        inputComment3 = new Comment();
        inputComment3.setPostId(1);
        inputComment3.setCommenterName("Gerald");
        inputComment3.setCreateDate(LocalDate.of(2021, 4, 20));
        inputComment3.setComment("That is sooooo interesting.");

        outputComment1 = new Comment();
        outputComment1.setCommentId(1);
        outputComment1.setPostId(1);
        outputComment1.setCommenterName("Jerry");
        outputComment1.setCreateDate(LocalDate.of(2021, 4, 19));
        outputComment1.setComment("Yummy");

        outputComment2 = new Comment();
        outputComment2.setCommentId(2);
        outputComment2.setPostId(2);
        outputComment2.setCommenterName("Gerald");
        outputComment2.setCreateDate(LocalDate.of(2021, 4, 18));
        outputComment2.setComment("I disagree");

        outputComment3 = new Comment();
        outputComment3.setCommentId(3);
        outputComment3.setPostId(1);
        outputComment3.setCommenterName("Gerald");
        outputComment3.setCreateDate(LocalDate.of(2021, 4, 20));
        outputComment3.setComment("That is sooooo interesting.");

        commentWithInvalidId = new Comment();
        commentWithInvalidId.setCommentId(5);
        commentWithInvalidId.setPostId(1);
        commentWithInvalidId.setCommenterName("Gerald");
        commentWithInvalidId.setCreateDate(LocalDate.of(2021, 4, 20));
        commentWithInvalidId.setComment("That is sooooo interesting.");

        allComments = new ArrayList<>();
        allComments.add(outputComment1);
        allComments.add(outputComment2);
        allComments.add(outputComment3);

        post1Comments = new ArrayList<>();
        post1Comments.add(outputComment1);
        post1Comments.add(outputComment3);

        post2Comments = new ArrayList<>();
        post2Comments.add(outputComment2);
    }
}