# socialmedia

A RESTful API for a  social media platform using Java Spring Boot and Maven.


Overview
The Social Media Platform project is a comprehensive social networking application designed to provide users with a versatile and engaging platform for connecting, sharing, and communicating. This documentation outlines the core functionalities and the technology stack used to build the application.

User Authentication and Authorization

User Registration and Profiles
Users can create accounts with unique usernames and email addresses.
User profiles include customizable information such as a bio and profile picture.
Users can edit and update their profiles.

Authentication
Secure authentication system with options for email verification.
Users can log in using their username and password.

Authorization
Define roles (e.g., regular user, admin) with different levels of access.
Admin users have the ability to disable, delete, and view all accounts and posts.

Content Management

Posting and Sharing
Users can create posts with text, images, and videos.
Commenting on posts is supported.
Reposting functionality allows users to share posts with their followers.

Privacy Settings
Customizable privacy settings for user posts:
Public: Visible to everyone.
Private: Only friends can see the posts.
Media Storage
Files are stored in Amazon S3.
URLs of the files are saved where required.

Social Features

Friendship and Following
Users can send and accept friend requests.
A "follow" system allows users to receive updates without a reciprocal connection.

Activity Feed
Personalized activity feed displaying posts from friends and followed users.
Notifications for likes, comments, and friend requests.

Messaging
Private messaging system for one-on-one or group communication.
Analytics

Backend
Java (Spring Boot)

Database
SQL


