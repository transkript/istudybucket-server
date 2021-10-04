package com.feljtech.istudybucket.business.mapper;

import com.feljtech.istudybucket.api.dto.CommentDto;
import com.feljtech.istudybucket.data.entity.Comment;
import com.feljtech.istudybucket.data.entity.Post;
import com.feljtech.istudybucket.data.entity.User;
import com.feljtech.istudybucket.business.util.InstantUtil;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

/**
 * @author Elroy Kanye
 */
@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "postId", expression = "java(mapPostId(comment.getSourcePost()))")
    @Mapping(target = "authorName", expression = "java(mapAuthorName(comment.getAuthor()))")
    @Mapping(target = "creationDate", expression = "java(mapCreationDate(comment.getCreationDate()))")
    CommentDto mapCommentToDto(Comment comment);

    @InheritInverseConfiguration
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "sourcePost", ignore = true)
    @Mapping(target = "creationDate", expression = "java(inverseMapCreationDate())")
    Comment mapDtoToComment(CommentDto commentDto);

    // map helpers
    default Long mapPostId(Post sourcePost) {
        return sourcePost.getPostId();
    }
    default String mapAuthorName(User author) {
        return author.getUsername();
    }
    default String mapCreationDate(Instant creationDate) {
        return InstantUtil.extractDateFromInstant(creationDate);
    }

    // inverse map helpers
    default Instant inverseMapCreationDate() {
        return Instant.now();
    }
}
