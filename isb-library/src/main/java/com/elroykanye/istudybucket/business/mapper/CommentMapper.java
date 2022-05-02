package com.elroykanye.istudybucket.business.mapper;

import com.elroykanye.istudybucket.api.dto.CommentDto;
import com.elroykanye.istudybucket.business.util.InstantUtil;
import com.elroykanye.istudybucket.data.entity.Comment;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.time.Instant;

/**
 * @author Elroy Kanye
 */
@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface CommentMapper {
    @Mappings({
            @Mapping(target = "postId", expression = "java(comment.getSourcePost().getPostId())"),
            @Mapping(target = "creationDate", expression = "java(mapCreationDate(comment.getCreationDate()))"),
            @Mapping(target = "authorId", expression = "java(comment.getAuthor().getUserId())"),
    })
    CommentDto mapCommentToDto(Comment comment);

    // map helpers
    default String mapCreationDate(Instant creationDate) {
        return InstantUtil.extractDateFromInstant(creationDate);
    }

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "author", ignore = true),
            @Mapping(target = "sourcePost", ignore = true),
            @Mapping(target = "creationDate", expression = "java(inverseMapCreationDate())"),
    })
    Comment mapDtoToComment(CommentDto commentDto);

    // inverse map helpers
    default Instant inverseMapCreationDate() {
        return Instant.now();
    }
}
