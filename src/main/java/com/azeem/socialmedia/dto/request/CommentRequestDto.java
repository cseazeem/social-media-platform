package com.azeem.socialmedia.dto.request;

public record CommentRequestDto(
        String content,
        String userId,
        String commentId
) {
}
