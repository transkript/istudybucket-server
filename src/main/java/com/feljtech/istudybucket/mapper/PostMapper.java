package com.feljtech.istudybucket.mapper;

import com.feljtech.istudybucket.dto.PostDto;
import com.feljtech.istudybucket.entity.Comment;
import com.feljtech.istudybucket.entity.Post;
import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.entity.Vote;
import com.feljtech.istudybucket.enums.PostType;
import com.feljtech.istudybucket.enums.VoteType;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;
import java.util.List;
import java.util.Locale;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface PostMapper {

    // post to dto mapping
    @Mapping(target = "commentCount", expression = "java(mapCommentCount(post.getComments()))")
    @Mapping(target = "authorName", expression = "java(mapAuthorName(post.getAuthor()))")
    @Mapping(target = "upVotes", expression = "java(mapUpVotes(post.getVotes()))")
    @Mapping(target = "downVotes", expression = "java(mapDownVotes(post.getVotes()))")
    PostDto mapPostToDto(Post post);

    // dto to post mapping
    @InheritInverseConfiguration
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "votes", ignore = true)
    @Mapping(target = "author", ignore = true)
    @Mapping(target = "creationDate", expression = "java(inverseMapCreationDate())")
    @Mapping(target = "postType", expression = "java(inverseMapPostType(postDto.getPostType()))")
    Post mapDtoToPost(PostDto postDto);

    /**
     * Extracts the number of comments from the post
     * @param comments the list of comments
     * @return the size of the list
     */
    default Integer mapCommentCount(List<Comment> comments) {
        return comments.size();
    }

    /**
     * Extracts the username from the user object of the post
     * @param user User object
     * @return username of the associated user
     */
    default String mapAuthorName(User user) {
        return user.getUsername();
    }

    /**
     * Converts the UserVotePost relation to an upvote count
     * @param postVotes the list of relations
     * @return the streamed count of all relations with a true value of vote
     */
    default Integer mapUpVotes(List<Vote> postVotes) {
        return Math.toIntExact(
                postVotes.stream()
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
     * generates an Instant object for a new post
     * @return the Instant object of the current time
     */
    default Instant inverseMapCreationDate() {
        return Instant.now();
    }

    /**
     * Converts the string representation of the post type to the enum
     * @param postType the string rep
     * @return the Enum of the string rep
     */
    default PostType inverseMapPostType(String postType) {
        return PostType.valueOf(postType.toUpperCase(Locale.ROOT));
    }

}
