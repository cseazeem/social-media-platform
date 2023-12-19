package com.azeem.socialmedia.dto.request;

public record TweetRequestDto(
        String content,
        String mediaUrl,
        String hashtags,
        String userId
) {
}
