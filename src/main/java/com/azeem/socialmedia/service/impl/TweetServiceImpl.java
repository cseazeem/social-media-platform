package com.azeem.socialmedia.service.impl;

import com.azeem.socialmedia.repository.TweetRepository;
import com.azeem.socialmedia.domain.Tweet;
import com.azeem.socialmedia.service.TweetService;
import com.azeem.socialmedia.utils.IdGenerator;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TweetServiceImpl implements TweetService {
    private final TweetRepository tweetRepository;
    private final IdGenerator idGenerator;

    public TweetServiceImpl(TweetRepository tweetRepository, IdGenerator idGenerator) {
        this.tweetRepository = tweetRepository;
        this.idGenerator = idGenerator;
    }

    @Override
    public Tweet save(Tweet tweet) {
        final long id = idGenerator.nextId();
        tweet.setTweetId(id + "");
        tweet.setTimestamp(LocalDateTime.now());
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet findById(String tweetId) {
        return tweetRepository.findById(tweetId).orElseThrow( /* handle exception */ );
    }

    @Override
    public Tweet update(String tweetId, Tweet tweet) {
        return tweetRepository.findById(tweetId).map(oldTweet -> {

            oldTweet.setContent(tweet.getContent());
            oldTweet.setMediaUrl(tweet.getMediaUrl());
            oldTweet.setTimestamp(LocalDateTime.now());
            oldTweet.setHashtags(tweet.getHashtags());

            return tweetRepository.save(oldTweet);
        }).orElseThrow( /* handle exception */ );
    }

    @Override
    public void delete(String tweetId) {
        tweetRepository.findById(tweetId).orElseThrow( /* handle exception */ );
        tweetRepository.deleteById(tweetId);
    }

    @Override
    public List<Tweet> findAll() {
        /* todo - implement pagination etc */
        return tweetRepository.findAll();
    }
}
