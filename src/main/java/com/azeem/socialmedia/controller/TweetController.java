package com.azeem.socialmedia.controller;

import com.azeem.socialmedia.dto.request.TweetRequestDto;
import com.azeem.socialmedia.converter.TweetRequestDtoToTweet;
import com.azeem.socialmedia.converter.TweetToTweetResponseDto;
import com.azeem.socialmedia.domain.Tweet;
import com.azeem.socialmedia.domain.User;
import com.azeem.socialmedia.dto.response.TweetResponseDto;
import com.azeem.socialmedia.service.TweetService;
import com.azeem.socialmedia.service.UserService;
import com.azeem.socialmedia.system.Result;
import com.azeem.socialmedia.system.StatusCode;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tweets")
public class TweetController {
    private final TweetService tweetService;
    private final UserService userService;
    private final TweetRequestDtoToTweet tweetRequestDtoToTweet;
    private final TweetToTweetResponseDto tweetToTweetResponseDto;

    public TweetController(TweetService tweetService,
                           UserService userService,
                           TweetRequestDtoToTweet tweetRequestDtoToTweet,
                           TweetToTweetResponseDto tweetToTweetResponseDto
    ) {

        this.tweetService = tweetService;
        this.userService = userService;
        this.tweetRequestDtoToTweet = tweetRequestDtoToTweet;
        this.tweetToTweetResponseDto = tweetToTweetResponseDto;
    }


    @PostMapping
    public Result addTweet(@RequestBody TweetRequestDto tweetRequestDto) {
        final String userId = tweetRequestDto.userId();
        User user = userService.findById(userId);

        Tweet tweet = tweetRequestDtoToTweet.convert(tweetRequestDto);
        assert tweet != null;
        tweet.setUser(user);

        Tweet savedTweet = tweetService.save(tweet);
        TweetResponseDto savedTweetResponseDto = tweetToTweetResponseDto.convert(savedTweet);

        return new Result(true, StatusCode.SUCCESS, "message - addTweet", savedTweetResponseDto);
    }

    @GetMapping("/{tweetId}")
    public Result getTweetById(@PathVariable String tweetId) {
        Tweet tweet = tweetService.findById(tweetId);
        TweetResponseDto tweetResponseDto = tweetToTweetResponseDto.convert(tweet);

        return new Result(true, StatusCode.SUCCESS, "message - getTweetById", tweetResponseDto);
    }

    @PutMapping("/{tweetId}")
    public Result updateTweet(@PathVariable String tweetId, @RequestBody TweetRequestDto tweetRequestDto) {
        Tweet tweet = tweetRequestDtoToTweet.convert(tweetRequestDto);
        Tweet updatedTweet = tweetService.update(tweetId, tweet);
        TweetResponseDto updatedTweetResponseDto = tweetToTweetResponseDto.convert(updatedTweet);
        return new Result(true, StatusCode.SUCCESS, "message - updateTweet", updatedTweetResponseDto);
    }

    @DeleteMapping("/{tweetId}")
    public Result deleteTweet(@PathVariable String tweetId) {
        tweetService.delete(tweetId);
        return new Result(true, StatusCode.SUCCESS, "message - deleteTweet");
    }

    @GetMapping
    public Result getAllTweet() {
        List<TweetResponseDto> allTweet = tweetService.findAll().stream().map((tweetToTweetResponseDto::convert)).toList();

        // todo
        return new Result(true, StatusCode.SUCCESS, "message - getAllTweet", allTweet);
    }
}
