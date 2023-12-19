package com.azeem.socialmedia.service;

import com.azeem.socialmedia.domain.Tweet;
import java.util.List;

public interface TweetService {
    Tweet save(Tweet tweet);
    Tweet findById(String tweetId);
    Tweet update(String tweetId, Tweet tweet);
    void delete(String tweetId);
    List<Tweet> findAll();
}
