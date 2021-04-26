package com.trilogyed.stwitter.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.trilogyed.stwitter.models.Comment;
import com.trilogyed.stwitter.models.PostViewModel;
import com.trilogyed.stwitter.service.ServiceLayer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
@WebMvcTest(StwitterController.class)
public class StwitterControllerTest {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @MockBean
    RabbitTemplate rabbitTemplate;

    @MockBean
    ServiceLayer service;

    PostViewModel inputPostViewModel1, inputPostViewModel2, inputPostViewModel3, outputPostViewModel1, outputPostViewModel2, outputPostViewModel3, postViewModelWithInvalidId;
    List<PostViewModel> stevePostViewModels, davidPostViewModels;

    @Before
    public void setUp() throws Exception {
        setupTestPosts();
        setupMockService();
    }

    @Test
    public void shouldSavePostAndReturnStatusCreated() throws Exception{
        String inputForTest1 = mapper.writeValueAsString(inputPostViewModel1);
        String inputForTest2 = mapper.writeValueAsString(inputPostViewModel2);
        String inputForTest3 = mapper.writeValueAsString(inputPostViewModel3);

        String expectedOutputForTest1 = mapper.writeValueAsString(outputPostViewModel1);
        String expectedOutputForTest2 = mapper.writeValueAsString(outputPostViewModel2);
        String expectedOutputForTest3 = mapper.writeValueAsString(outputPostViewModel3);

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
    public void shouldGetPostAndReturnStatusOK() throws Exception{
        String test1 = "/posts/1";
        String test2 = "/posts/2";
        String test3 = "/posts/3";

        String expectedOutputForTest1 = mapper.writeValueAsString(outputPostViewModel1);
        String expectedOutputForTest2 = mapper.writeValueAsString(outputPostViewModel2);
        String expectedOutputForTest3 = mapper.writeValueAsString(outputPostViewModel3);

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
    public void shouldGetPostsByPosterNameAndReturnStatusOk() throws Exception{
        String test1 = "/posts/user/Steve";
        String test2 = "/posts/user/David";

        String expectedOutputFromTest1 = mapper.writeValueAsString(stevePostViewModels);
        String expectedOutputFromTest2 = mapper.writeValueAsString(davidPostViewModels);

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
    public void shouldThrowUnprocessableEntityStatusWhenInvalidPostIsSentToAdd() throws Exception{
        Comment inputComment1 = new Comment();
        inputComment1.setPostId(1);
        inputComment1.setCommenterName("Jerry");
        inputComment1.setCreateDate(LocalDate.of(2021, 4, 19));
        inputComment1.setComment("Yummy");

        Comment inputComment2 = new Comment();
        inputComment2.setPostId(2);
        inputComment2.setCommenterName("Gerald");
        inputComment2.setCreateDate(LocalDate.of(2021, 4, 18));
        inputComment2.setComment("I disagree");

        Comment inputComment3 = new Comment();
        inputComment3.setPostId(1);
        inputComment3.setCommenterName("Gerald");
        inputComment3.setCreateDate(LocalDate.of(2021, 4, 20));
        inputComment3.setComment("That is sooooo interesting.");

        List<Comment> post1Comments = new ArrayList<>();
        post1Comments.add(inputComment1);
        post1Comments.add(inputComment3);

        List<Comment> post2Comments = new ArrayList<>();
        post2Comments.add(inputComment2);

        Comment invalidInputComment1 = new Comment();
        invalidInputComment1.setCommenterName("Jerry");
        invalidInputComment1.setCreateDate(LocalDate.of(2021, 4, 19));
        invalidInputComment1.setComment("Yummy");

        Comment invalidInputComment2 = new Comment();
        invalidInputComment2.setCreateDate(LocalDate.of(2021, 4, 18));
        invalidInputComment2.setComment("I disagree");

        Comment invalidInputComment3 = new Comment();
        invalidInputComment3.setCommenterName("Gerald");
        invalidInputComment3.setCreateDate(LocalDate.of(2021, 4, 20));
        invalidInputComment3.setComment("That is sooooo interesting.");

        Comment invalidInputComment4 = new Comment();
        invalidInputComment4.setCommenterName("David");
        invalidInputComment4.setComment("lol");

        List<Comment> post1InvalidComments = new ArrayList<>();
        post1InvalidComments.add(invalidInputComment1);
        post1InvalidComments.add(invalidInputComment3);

        List<Comment> post2InvalidComments = new ArrayList<>();
        post2InvalidComments.add(invalidInputComment2);

        List<Comment> post3InvalidComments = new ArrayList<>();
        post3InvalidComments.add(invalidInputComment4);

        PostViewModel postWithCommentMissingPostViewModelId = new PostViewModel();
        postWithCommentMissingPostViewModelId.setPostDate(LocalDate.of(2021, 4, 19));
        postWithCommentMissingPostViewModelId.setPosterName("Steve");
        postWithCommentMissingPostViewModelId.setPostContent("Look at my food.");
        postWithCommentMissingPostViewModelId.setComments(post1InvalidComments);

        PostViewModel postViewModelWithCommentMissingCommenterName = new PostViewModel();
        postViewModelWithCommentMissingCommenterName.setPostDate(LocalDate.of(2021, 3, 9));
        postViewModelWithCommentMissingCommenterName.setPosterName("David");
        postViewModelWithCommentMissingCommenterName.setPostContent("I have an opinion.");
        postViewModelWithCommentMissingCommenterName.setComments(post2InvalidComments);

        PostViewModel postViewModelWithCommentMissingDate = new PostViewModel();
        postViewModelWithCommentMissingDate.setPostDate(LocalDate.of(2021, 2, 22));
        postViewModelWithCommentMissingDate.setPosterName("Steve");
        postViewModelWithCommentMissingDate.setPostContent("Meme.");
        postViewModelWithCommentMissingDate.setComments(post3InvalidComments);

        PostViewModel postMissingPostViewModelDate = new PostViewModel();
        postMissingPostViewModelDate.setPosterName("Steve");
        postMissingPostViewModelDate.setPostContent("Look at my food.");
        postMissingPostViewModelDate.setComments(post1Comments);

        PostViewModel postViewModelMissingPosterName = new PostViewModel();
        postViewModelMissingPosterName.setPostDate(LocalDate.of(2021, 3, 9));
        postViewModelMissingPosterName.setPostContent("I have an opinion.");
        postViewModelMissingPosterName.setComments(post2Comments);

//        doThrow(MethodArgumentNotValidException.class).when(service).createPost(postViewModelWithCommentMissingDate);
//        doThrow(MethodArgumentNotValidException.class).when(service).createPost(postViewModelWithCommentMissingDate);

        String inputForTest1 = mapper.writeValueAsString(postViewModelWithCommentMissingCommenterName);
        String inputForTest2 = mapper.writeValueAsString(postViewModelWithCommentMissingDate);
        String inputForTest3 = mapper.writeValueAsString(postMissingPostViewModelDate);
        String inputForTest4 = mapper.writeValueAsString(postViewModelMissingPosterName);

//        mockMvc.perform(
//                post("/posts")
//                        .content(inputForTest1)
//                        .contentType(MediaType.APPLICATION_JSON)
//        )
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity());
//
//        mockMvc.perform(
//                post("/posts")
//                        .content(inputForTest2)
//                        .contentType(MediaType.APPLICATION_JSON)
//        )
//                .andDo(print())
//                .andExpect(status().isUnprocessableEntity());
//

        mockMvc.perform(
                post("/posts")
                        .content(inputForTest3)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

        mockMvc.perform(
                post("/posts")
                        .content(inputForTest4)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());

    }

    private void setupMockService(){
        doReturn(outputPostViewModel1).when(service).createPost(inputPostViewModel1);
        doReturn(outputPostViewModel2).when(service).createPost(inputPostViewModel2);
        doReturn(outputPostViewModel3).when(service).createPost(inputPostViewModel3);
        doReturn(outputPostViewModel1).when(service).getPostById(1);
        doReturn(outputPostViewModel2).when(service).getPostById(2);
        doReturn(outputPostViewModel3).when(service).getPostById(3);
        doReturn(stevePostViewModels).when(service).getPostByPosterName("Steve");
        doReturn(davidPostViewModels).when(service).getPostByPosterName("David");
    }

    private void setupTestPosts(){
        Comment inputComment1 = new Comment();
        inputComment1.setCommenterName("Jerry");
        inputComment1.setCreateDate(LocalDate.of(2021, 4, 19));
        inputComment1.setComment("Yummy");

        Comment inputComment2 = new Comment();
        inputComment2.setCommenterName("Gerald");
        inputComment2.setCreateDate(LocalDate.of(2021, 4, 18));
        inputComment2.setComment("I disagree");

        Comment inputComment3 = new Comment();
        inputComment3.setCommenterName("Gerald");
        inputComment3.setCreateDate(LocalDate.of(2021, 4, 20));
        inputComment3.setComment("That is sooooo interesting.");

        Comment inputComment4 = new Comment();
        inputComment4.setCommenterName("David");
        inputComment4.setCreateDate(LocalDate.of(2021, 2, 23));
        inputComment4.setComment("lol");

        Comment outputComment1 = new Comment();
        outputComment1.setCommentId(1);
        outputComment1.setPostId(1);
        outputComment1.setCommenterName("Jerry");
        outputComment1.setCreateDate(LocalDate.of(2021, 4, 19));
        outputComment1.setComment("Yummy");

        Comment outputComment2 = new Comment();
        outputComment2.setCommentId(2);
        outputComment2.setPostId(2);
        outputComment2.setCommenterName("Gerald");
        outputComment2.setCreateDate(LocalDate.of(2021, 4, 18));
        outputComment2.setComment("I disagree");

        Comment outputComment3 = new Comment();
        outputComment3.setCommentId(3);
        outputComment3.setPostId(1);
        outputComment3.setCommenterName("Gerald");
        outputComment3.setCreateDate(LocalDate.of(2021, 4, 20));
        outputComment3.setComment("That is sooooo interesting.");

        Comment outputComment4 = new Comment();
        outputComment4.setCommentId(4);
        outputComment4.setPostId(3);
        outputComment4.setCommenterName("David");
        outputComment4.setCreateDate(LocalDate.of(2021, 2, 23));
        outputComment4.setComment("lol");

        List<Comment> post1InputComments = new ArrayList<>();
        post1InputComments.add(inputComment1);
        post1InputComments.add(inputComment3);

        List<Comment> post2InputComments = new ArrayList<>();
        post2InputComments.add(inputComment2);

        List<Comment> post3InputComments = new ArrayList<>();
        post3InputComments.add(inputComment4);

        List<Comment> post1OutputComments = new ArrayList<>();
        post1OutputComments.add(outputComment1);
        post1OutputComments.add(outputComment3);

        List<Comment> post2OutputComments = new ArrayList<>();
        post2OutputComments.add(outputComment2);

        List<Comment> post3OutputComments = new ArrayList<>();
        post3OutputComments.add(inputComment4);

        inputPostViewModel1 = new PostViewModel();
        inputPostViewModel1.setPostDate(LocalDate.of(2021, 4, 19));
        inputPostViewModel1.setPosterName("Steve");
        inputPostViewModel1.setPostContent("Look at my food.");
        inputPostViewModel1.setComments(post1InputComments);

        inputPostViewModel2 = new PostViewModel();
        inputPostViewModel2.setPostDate(LocalDate.of(2021, 3, 9));
        inputPostViewModel2.setPosterName("David");
        inputPostViewModel2.setPostContent("I have an opinion.");
        inputPostViewModel2.setComments(post2InputComments);

        inputPostViewModel3 = new PostViewModel();
        inputPostViewModel3.setPostDate(LocalDate.of(2021, 2, 22));
        inputPostViewModel3.setPosterName("Steve");
        inputPostViewModel3.setPostContent("Meme.");
        inputPostViewModel3.setComments(post3InputComments);

        outputPostViewModel1 = new PostViewModel();
        outputPostViewModel1.setPostId(1);
        outputPostViewModel1.setPostDate(LocalDate.of(2021, 4, 19));
        outputPostViewModel1.setPosterName("Steve");
        outputPostViewModel1.setPostContent("Look at my food.");
        outputPostViewModel1.setComments(post1OutputComments);

        outputPostViewModel2 = new PostViewModel();
        outputPostViewModel2.setPostId(2);
        outputPostViewModel2.setPostDate(LocalDate.of(2021, 3, 9));
        outputPostViewModel2.setPosterName("David");
        outputPostViewModel2.setPostContent("I have an opinion.");
        outputPostViewModel2.setComments(post2OutputComments);

        outputPostViewModel3 = new PostViewModel();
        outputPostViewModel3.setPostId(3);
        outputPostViewModel3.setPostDate(LocalDate.of(2021, 2, 22));
        outputPostViewModel3.setPosterName("Steve");
        outputPostViewModel3.setPostContent("Meme.");
        outputPostViewModel3.setComments(post3OutputComments);

        stevePostViewModels = new ArrayList<>();
        stevePostViewModels.add(outputPostViewModel1);
        stevePostViewModels.add(outputPostViewModel3);

        davidPostViewModels = new ArrayList<>();
        davidPostViewModels.add(outputPostViewModel2);
    }
}