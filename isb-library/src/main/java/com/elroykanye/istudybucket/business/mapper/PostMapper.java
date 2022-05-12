package com.elroykanye.istudybucket.business.mapper;

import com.elroykanye.istudybucket.api.dto.PostDto;
import com.elroykanye.istudybucket.data.entity.Post;
import com.elroykanye.istudybucket.data.entity.Vote;
import com.elroykanye.istudybucket.data.enums.PostType;
import com.elroykanye.istudybucket.data.enums.VoteType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;
import java.util.Locale;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface PostMapper {

    // post to dto mapping
    @Mappings({
            @Mapping(target = "commentCount", expression = "java(post.getComments().size())"),
            @Mapping(target = "authorId", expression = "java(post.getAuthor().getUserId())"),
            @Mapping(target = "upVotes", expression = "java(mapUpVotes(post.getVotes()))"),
            @Mapping(target = "downVotes", expression = "java(mapDownVotes(post.getVotes()))"),
            @Mapping(target = "voteCount", expression = "java(post.getVotes().size())"),
            @Mapping(target = "sourcePostId", expression = "java(mapSourcePost(post))"),
    })
    PostDto mapPostToDto(Post post);

    // dto to post mapping
    @InheritInverseConfiguration
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "sourcePost", ignore = true)
    @Mapping(target = "postType", expression = "java(inverseMapPostType(postDto.getPostType()))")
    Post mapDtoToPost(PostDto postDto);


    /**
     * Converts the UserVotePost relation to an upvote count
     * @param postVotes the list of relations
     * @return the streamed count of all relations with a true value of vote
     */
    default Integer mapUpVotes(List<Vote> postVotes) {
        return Math.toIntExact(postVotes.stream()
                .filter(vote -> VoteType.valueOf(vote.getVote()) == 1)
                .count()
        );
    }

    /**
     * Converts the UserVotePost relation to an upvote count
     * @param postVotes the list of relations
     * @return the streamed count of all relations with a true value of vote
     */
    default Integer mapDownVotes(List<Vote> postVotes) {
        return Math.toIntExact(
                postVotes.stream()
                        .filter(vote -> VoteType.valueOf(vote.getVote()) == -1)
                        .count()
        );
    }

    /**
     * Gets the source post id if the post is a reply
     * @param post the post
     * @return the source post id if the post is a reply
     */
    default Long mapSourcePost(Post post) {
        if(post.getSourcePost() != null) {
            return post.getSourcePost().getPostId();
        } return null;
    }

    /**
     * Converts the string representation of the post type to the enum
     * @param postType the string rep
     * @return the Enum of the string rep
     */
    default PostType inverseMapPostType(String postType) {
        if(postType == null) return PostType.DEFAULT;
        return switch(postType.toLowerCase(Locale.ENGLISH)) {
            case "question" -> PostType.QUESTION;
            case "resource" -> PostType.RESOURCE;
            case "comment" -> PostType.COMMENT;
            default -> PostType.DEFAULT;
        };
    }
}
