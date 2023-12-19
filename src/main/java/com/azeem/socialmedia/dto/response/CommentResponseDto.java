package com.azeem.socialmedia.dto.response;

import java.time.LocalDateTime;

public record CommentResponseDto(
        String commentId,
        String content,
        LocalDateTime timestamp
) {
}
