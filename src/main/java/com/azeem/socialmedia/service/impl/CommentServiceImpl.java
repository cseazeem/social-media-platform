package com.azeem.socialmedia.service.impl;

import com.azeem.socialmedia.repository.CommentRepository;
import com.azeem.socialmedia.domain.Comment;
import com.azeem.socialmedia.service.CommentService;
import com.azeem.socialmedia.utils.IdGenerator;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final IdGenerator idGenerator;

    public CommentServiceImpl(CommentRepository commentRepository, IdGenerator idGenerator) {
        this.commentRepository = commentRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Comment save(Comment comment) {
        long id = idGenerator.nextId();
        comment.setCommentId(id + "");
        comment.setTimestamp(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public Comment findById(String commentId) {
        return commentRepository.findById(commentId).orElseThrow( /* handle exception */ );
    }

    @Override
    public Comment update(String commentId, Comment comment) {
        return commentRepository.findById(commentId).map(oldComment -> {

            oldComment.setContent(comment.getContent());
            oldComment.setTimestamp(LocalDateTime.now());

            return commentRepository.save(oldComment);
        }).orElseThrow( /* handle exception */ );
    }

    @Override
    public void delete(String commentId) {
        commentRepository.findById(commentId).orElseThrow( /* handle exception */ );
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<Comment> findAll() {
        /* todo - implement pagination etc */
        return commentRepository.findAll();
    }
}
