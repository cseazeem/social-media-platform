package com.azeem.socialmedia.converter;

import com.azeem.socialmedia.domain.Comment;
import com.azeem.socialmedia.dto.response.CommentResponseDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class CommentToCommentResponseDto implements Converter<Comment, CommentResponseDto> {
    @Override
    public CommentResponseDto convert(Comment source) {
        return new CommentResponseDto(
                source.getCommentId(),
                source.getContent(),
                source.getTimestamp()
        );
    }
}
