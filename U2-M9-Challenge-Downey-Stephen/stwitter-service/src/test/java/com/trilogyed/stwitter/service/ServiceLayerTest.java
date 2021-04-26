package com.trilogyed.stwitter.service;

import com.trilogyed.stwitter.feign.CommentServiceFeignClient;
import com.trilogyed.stwitter.feign.PostServiceFeignClient;
import com.trilogyed.stwitter.models.Comment;
import com.trilogyed.stwitter.models.Post;
import com.trilogyed.stwitter.models.PostViewModel;
import org.junit.Before;
import org.junit.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class ServiceLayerTest {

    ServiceLayer service;
    CommentServiceFeignClient commentClient;
    PostServiceFeignClient postClient;
    RabbitTemplate rabbitTemplate;

    //Test Comments
    Comment inputComment1, inputComment2, inputComment3, inputComment4,
            outputComment1, outputComment2, outputComment3, outputComment4;
    List<Comment> post1InputComments, post2InputComments, post3InputComments,
            post1OutputComments, post2OutputComments, post3OutputComments;

    //Test Posts
    Post inputPost1, inputPost2, inputPost3, outputPost1, outputPost2, outputPost3;
    List<Post> stevePosts, davidPosts;

    //Test PostViewModels
    PostViewModel inputPostViewModel1, inputPostViewModel2, inputPostViewModel3,
            outputPostViewModel1, outputPostViewModel2, outputPostViewModel3;
    List<PostViewModel> stevePostViewModels, davidPostViewModels;

    @Before
    public void setUp() throws Exception {
        setupTestComments();
        setupTestPosts();
        setupTestPostViewModels();

        setupMockCommentClient();
        setupMockPostClient();
        setupMockRabbitTemplate();

        service = new ServiceLayer(commentClient, postClient, rabbitTemplate);
    }

    @Test
    public void shouldCreatePost() {
        PostViewModel outputPostVMForCreateMethod1 = new PostViewModel();
        outputPostVMForCreateMethod1.setPostId(1);
        outputPostVMForCreateMethod1.setPostDate(LocalDate.of(2021, 4, 19));
        outputPostVMForCreateMethod1.setPosterName("Steve");
        outputPostVMForCreateMethod1.setPostContent("Look at my food.");
        outputPostVMForCreateMethod1.setComments(new ArrayList<>());

        PostViewModel outputPostVMForCreateMethod2 = new PostViewModel();
        outputPostVMForCreateMethod2.setPostId(2);
        outputPostVMForCreateMethod2.setPostDate(LocalDate.of(2021, 3, 9));
        outputPostVMForCreateMethod2.setPosterName("David");
        outputPostVMForCreateMethod2.setPostContent("I have an opinion.");
        outputPostVMForCreateMethod2.setComments(new ArrayList<>());

        PostViewModel outputPostVMForCreateMethod3 = new PostViewModel();
        outputPostVMForCreateMethod3.setPostId(3);
        outputPostVMForCreateMethod3.setPostDate(LocalDate.of(2021, 2, 22));
        outputPostVMForCreateMethod3.setPosterName("Steve");
        outputPostVMForCreateMethod3.setPostContent("Meme.");
        outputPostVMForCreateMethod3.setComments(new ArrayList<>());

        PostViewModel actualTestOutput1 = service.createPost(inputPostViewModel1);
        PostViewModel actualTestOutput2 = service.createPost(inputPostViewModel2);
        PostViewModel actualTestOutput3 = service.createPost(inputPostViewModel3);

        assertEquals(outputPostVMForCreateMethod1, actualTestOutput1);
        assertEquals(outputPostVMForCreateMethod2, actualTestOutput2);
        assertEquals(outputPostVMForCreateMethod3, actualTestOutput3);
    }

    @Test
    public void shouldGetPostById() {
        PostViewModel actualTestOutput1 = service.getPostById(1);
        PostViewModel actualTestOutput2 = service.getPostById(2);
        PostViewModel actualTestOutput3 = service.getPostById(3);

        assertEquals(outputPostViewModel1, actualTestOutput1);
        assertEquals(outputPostViewModel2, actualTestOutput2);
        assertEquals(outputPostViewModel3, actualTestOutput3);
    }

    @Test
    public void shouldGetPostByPosterName() {
        List<PostViewModel> actualTestOutput1 = service.getPostByPosterName("Steve");
        List<PostViewModel> actualTestOutput2 = service.getPostByPosterName("David");

        assertEquals(stevePostViewModels, actualTestOutput1);
        assertEquals(davidPostViewModels, actualTestOutput2);
    }

    private void setupMockPostClient(){
        postClient = mock(PostServiceFeignClient.class);

        doReturn(outputPost1).when(postClient).createPost(inputPost1);
        doReturn(outputPost2).when(postClient).createPost(inputPost2);
        doReturn(outputPost3).when(postClient).createPost(inputPost3);
        doReturn(outputPost1).when(postClient).getPost(1);
        doReturn(outputPost2).when(postClient).getPost(2);
        doReturn(outputPost3).when(postClient).getPost(3);
        doReturn(stevePosts).when(postClient).getPostsByPosterName("Steve");
        doReturn(davidPosts).when(postClient).getPostsByPosterName("David");
    }

    private void setupMockCommentClient(){
        commentClient = mock(CommentServiceFeignClient.class);

        doReturn(outputComment1).when(commentClient).createComment(inputComment1);
        doReturn(outputComment2).when(commentClient).createComment(inputComment2);
        doReturn(outputComment3).when(commentClient).createComment(inputComment3);
        doReturn(outputComment4).when(commentClient).createComment(inputComment4);
        doReturn(post1OutputComments).when(commentClient).getCommentsByPostId(1);
        doReturn(post2OutputComments).when(commentClient).getCommentsByPostId(2);
        doReturn(post3OutputComments).when(commentClient).getCommentsByPostId(3);
    }

    private void setupMockRabbitTemplate(){
        rabbitTemplate = mock(RabbitTemplate.class);
    }

    private void setupTestPostViewModels(){
        Comment viewModelInputComment1 = new Comment();
        viewModelInputComment1.setPostId(1);
        viewModelInputComment1.setCommenterName("Jerry");
        viewModelInputComment1.setCreateDate(LocalDate.of(2021, 4, 19));
        viewModelInputComment1.setComment("Yummy");

        Comment viewModelInputComment2 = new Comment();
        viewModelInputComment2.setPostId(2);
        viewModelInputComment2.setCommenterName("Gerald");
        viewModelInputComment2.setCreateDate(LocalDate.of(2021, 4, 18));
        viewModelInputComment2.setComment("I disagree");

        Comment viewModelInputComment3 = new Comment();
        viewModelInputComment3.setPostId(1);
        viewModelInputComment3.setCommenterName("Gerald");
        viewModelInputComment3.setCreateDate(LocalDate.of(2021, 4, 20));
        viewModelInputComment3.setComment("That is sooooo interesting.");

        Comment viewModelInputComment4 = new Comment();
        viewModelInputComment4.setPostId(3);
        viewModelInputComment4.setCommenterName("David");
        viewModelInputComment4.setCreateDate(LocalDate.of(2021, 2, 23));
        viewModelInputComment4.setComment("lol");

        List<Comment> postViewModel1InputComments = new ArrayList<>();
        postViewModel1InputComments.add(inputComment1);
        postViewModel1InputComments.add(inputComment3);

        List<Comment> postViewModel2InputComments = new ArrayList<>();
        postViewModel2InputComments.add(inputComment2);

        List<Comment> postViewModel3InputComments = new ArrayList<>();
        postViewModel3InputComments.add(inputComment4);

        inputPostViewModel1 = new PostViewModel();
        inputPostViewModel1.setPostDate(LocalDate.of(2021, 4, 19));
        inputPostViewModel1.setPosterName("Steve");
        inputPostViewModel1.setPostContent("Look at my food.");
        inputPostViewModel1.setComments(postViewModel1InputComments);

        inputPostViewModel2 = new PostViewModel();
        inputPostViewModel2.setPostDate(LocalDate.of(2021, 3, 9));
        inputPostViewModel2.setPosterName("David");
        inputPostViewModel2.setPostContent("I have an opinion.");
        inputPostViewModel2.setComments(postViewModel2InputComments);

        inputPostViewModel3 = new PostViewModel();
        inputPostViewModel3.setPostDate(LocalDate.of(2021, 2, 22));
        inputPostViewModel3.setPosterName("Steve");
        inputPostViewModel3.setPostContent("Meme.");
        inputPostViewModel3.setComments(postViewModel3InputComments);

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

        stevePosts = new ArrayList<>();
        stevePosts.add(outputPost1);
        stevePosts.add(outputPost3);

        davidPosts = new ArrayList<>();
        davidPosts.add(outputPost2);
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

        inputComment4 = new Comment();
        inputComment4.setPostId(3);
        inputComment4.setCommenterName("David");
        inputComment4.setCreateDate(LocalDate.of(2021, 2, 23));
        inputComment4.setComment("lol");

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

        outputComment4 = new Comment();
        outputComment4.setCommentId(4);
        outputComment4.setPostId(3);
        outputComment4.setCommenterName("David");
        outputComment4.setCreateDate(LocalDate.of(2021, 2, 23));
        outputComment4.setComment("lol");

        post1InputComments = new ArrayList<>();
        post1InputComments.add(inputComment1);
        post1InputComments.add(inputComment3);

        post2InputComments = new ArrayList<>();
        post2InputComments.add(inputComment2);

        post3InputComments = new ArrayList<>();
        post3InputComments.add(inputComment4);

        post1OutputComments = new ArrayList<>();
        post1OutputComments.add(outputComment1);
        post1OutputComments.add(outputComment3);

        post2OutputComments = new ArrayList<>();
        post2OutputComments.add(outputComment2);

        post3OutputComments = new ArrayList<>();
        post3OutputComments.add(outputComment4);
    }
}