package com.trilogyed.stwitter.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class PostViewModel {
    private Integer postId;
    private String postContent;
    @NotNull(message = "Post date cannot be null.")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate postDate;
    @NotNull(message = "Poster name cannot be null.")
    private String posterName;
    private List<Comment> comments;

    public Integer getPostId() {
        return postId;
    }

    public void setPostId(Integer postId) {
        this.postId = postId;
    }

    public String getPostContent() {
        return postContent;
    }

    public void setPostContent(String postContent) {
        this.postContent = postContent;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public void setPostDate(LocalDate postDate) {
        this.postDate = postDate;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setPosterName(String posterName) {
        this.posterName = posterName;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostViewModel postViewModel = (PostViewModel) o;
        return Objects.equals(postId, postViewModel.postId) && Objects.equals(postContent, postViewModel.postContent) && Objects.equals(postDate, postViewModel.postDate) && Objects.equals(posterName, postViewModel.posterName) && Objects.equals(comments, postViewModel.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, postContent, postDate, posterName, comments);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", postContent='" + postContent + '\'' +
                ", postDate=" + postDate +
                ", posterName='" + posterName + '\'' +
                ", comments=" + comments +
                '}';
    }
}
