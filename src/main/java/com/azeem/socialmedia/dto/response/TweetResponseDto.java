package com.azeem.socialmedia.dto.response;

import java.time.LocalDateTime;

public record TweetResponseDto (
        String tweetId,
        String content,
        String mediaUrl,
        LocalDateTime timestamp,
        String hashtags
) {
}
