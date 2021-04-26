package com.trilogyed.comment.dao;

import com.trilogyed.comment.exceptions.JdbcOperationFailedException;
import com.trilogyed.comment.model.Comment;
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
public class CommentDaoJdbcTemplateImplTest {

    @Autowired
    CommentDao dao;

    Comment inputComment1, inputComment2, inputComment3;

    @Before
    public void setUp() throws Exception {

        List<Comment> comments = dao.getAllComments();
        for (Comment c: comments) {
            dao.deleteComment(c.getCommentId());
        }

        setupTestComments();
    }

    @Test
    public void shouldAddGetDeleteComment() throws JdbcOperationFailedException {
        //Act
        inputComment1 = dao.createComment(inputComment1);

        Comment commentThatWasGotten = dao.getComment(inputComment1.getCommentId());

        //Assert
        assertEquals(inputComment1, commentThatWasGotten);

        //Act
        dao.deleteComment(inputComment1.getCommentId());

        List<Comment> comments = dao.getAllComments();

        //Assert
        assertEquals(0, comments.size());
    }

    @Test
    public void shouldGetCommentsByPostId() {
        inputComment1 = dao.createComment(inputComment1);
        inputComment2 = dao.createComment(inputComment2);
        inputComment3 = dao.createComment(inputComment3);

        List<Comment> post1Comments = dao.getCommentByPostId(1);
        List<Comment> post2Comments = dao.getCommentByPostId(2);

        assertEquals(2, post1Comments.size());
        assertEquals(1, post2Comments.size());
    }

    @Test
    public void shouldGetAllComments() throws JdbcOperationFailedException{
        inputComment1 = dao.createComment(inputComment1);

        //Act
        List<Comment> allCommentsInDatabase = dao.getAllComments();

        //Assert
        assertEquals(1, allCommentsInDatabase.size());

        inputComment2 = dao.createComment(inputComment2);

        //Act
        allCommentsInDatabase = dao.getAllComments();

        //Assert
        assertEquals(2, allCommentsInDatabase.size());


        dao.deleteComment(inputComment1.getCommentId());

        //Act
        allCommentsInDatabase = dao.getAllComments();

        //Assert
        assertEquals(1, allCommentsInDatabase.size());
        assertEquals(inputComment2, allCommentsInDatabase.get(0));
    }

    @Test
    public void shouldUpdateComment() throws JdbcOperationFailedException{
        Comment expectedOutputFromTest = dao.createComment(inputComment1);
        expectedOutputFromTest.setComment("Come to my show.");

        //Act
        dao.updateComment(expectedOutputFromTest);

        Comment actualOutputFromTest = dao.getComment(inputComment1.getCommentId());

        //Assert
        assertEquals(expectedOutputFromTest, actualOutputFromTest);
    }

    @Test
    public void shouldDeleteComment() throws JdbcOperationFailedException{
        inputComment1 = dao.createComment(inputComment1);
        inputComment2 = dao.createComment(inputComment2);
        List<Comment> commentList = dao.getAllComments();
        assertEquals(2, commentList.size());


        //Act
        dao.deleteComment(inputComment1.getCommentId());
        commentList = dao.getAllComments();

        //Assert
        assertEquals(1, commentList.size());
        assertNotEquals(inputComment1, commentList.get(0));

        //Act
        dao.deleteComment(inputComment2.getCommentId());
        commentList = dao.getAllComments();

        //Assert
        assertEquals(0, commentList.size());
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
    }
}