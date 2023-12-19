package com.azeem.socialmedia.service;

import com.azeem.socialmedia.domain.Comment;

import java.util.List;

public interface CommentService {
    Comment save(Comment comment);
    Comment findById(String commentId);
    Comment update(String commentId, Comment comment);
    void delete(String commentId);
    List<Comment> findAll();
}
