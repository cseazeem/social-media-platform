package com.azeem.socialmedia.repository;

import com.azeem.socialmedia.domain.Tweet;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface TweetRepository extends JpaRepository<Tweet, String> {
}
