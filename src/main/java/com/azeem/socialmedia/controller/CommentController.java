package com.azeem.socialmedia.controller;

import com.azeem.socialmedia.converter.CommentRequestDtoToComment;
import com.azeem.socialmedia.converter.CommentToCommentResponseDto;
import com.azeem.socialmedia.domain.Comment;
import com.azeem.socialmedia.domain.Tweet;
import com.azeem.socialmedia.domain.User;
import com.azeem.socialmedia.dto.request.CommentRequestDto;
import com.azeem.socialmedia.dto.response.CommentResponseDto;
import com.azeem.socialmedia.service.CommentService;
import com.azeem.socialmedia.service.TweetService;
import com.azeem.socialmedia.service.UserService;
import com.azeem.socialmedia.system.Result;
import com.azeem.socialmedia.system.StatusCode;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    private final CommentService commentService;
    private final UserService userService;
    private final TweetService tweetService;
    private final CommentRequestDtoToComment commentRequestDtoToComment;
    private final CommentToCommentResponseDto commentToCommentResponseDto;

    public CommentController(CommentService commentService,
                             UserService userService,
                             TweetService tweetService,
                             CommentRequestDtoToComment commentRequestDtoToComment,
                             CommentToCommentResponseDto commentToCommentResponseDto) {

        this.commentService = commentService;
        this.userService = userService;
        this.tweetService = tweetService;
        this.commentRequestDtoToComment = commentRequestDtoToComment;
        this.commentToCommentResponseDto = commentToCommentResponseDto;
    }


    @PostMapping
    public Result addComment(@RequestBody CommentRequestDto commentRequestDto) {
        final String userId = commentRequestDto.userId();
        final User user = userService.findById(userId);

        final String tweetId = commentRequestDto.commentId();
        final Tweet tweet = tweetService.findById(tweetId);

        Comment comment = commentRequestDtoToComment.convert(commentRequestDto);
        assert comment != null;

        comment.setUser(user);
        comment.setTweet(tweet);

        Comment savedComment = commentService.save(comment);
        CommentResponseDto savedCommentResponseDto = commentToCommentResponseDto.convert(savedComment);
        return new Result(true, StatusCode.SUCCESS, "message - addComment", savedCommentResponseDto);
    }

    @GetMapping("/{commentId}")
    public Result getCommentById(@PathVariable String commentId) {
        Comment comment = commentService.findById(commentId);
        CommentResponseDto commentResponseDto = commentToCommentResponseDto.convert(comment);
        return new Result(true, StatusCode.SUCCESS, "message - getCommentById", commentResponseDto);
    }

    @PutMapping("/{commentId}")
    public Result updateComment(@PathVariable String commentId, @RequestBody CommentRequestDto commentRequestDto) {
        Comment comment = commentRequestDtoToComment.convert(commentRequestDto);
        Comment updatedComment = commentService.update(commentId, comment);
        CommentResponseDto updatedCommentResponseDto = commentToCommentResponseDto.convert(updatedComment);
        return new Result(true, StatusCode.SUCCESS, "message - updateComment", updatedCommentResponseDto);
    }

    @DeleteMapping("/{commentId}")
    public Result deleteComment(@PathVariable String commentId) {
        commentService.delete(commentId);
        return new Result(true, StatusCode.SUCCESS, "message - deleteComment");
    }

    @GetMapping
    public Result getAllComment() {
        List<CommentResponseDto> allComment = commentService.findAll().stream().map(commentToCommentResponseDto::convert).toList();

        // todo
        return new Result(true, StatusCode.SUCCESS, "message - getAllComment", allComment);
    }
}
