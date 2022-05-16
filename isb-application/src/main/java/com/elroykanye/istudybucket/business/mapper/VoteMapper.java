package com.elroykanye.istudybucket.business.mapper;

import com.elroykanye.istudybucket.api.dto.VoteDto;
import com.elroykanye.istudybucket.data.entity.Vote;
import com.elroykanye.istudybucket.data.enums.VoteType;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring", implementationPackage = "<PACKAGE_NAME>.impl")
public interface VoteMapper {
    @Mappings({
            @Mapping(target = "postId", expression = "java(vote.getPost().getPostId())"),
            @Mapping(target = "userId", expression = "java(vote.getUser().getId())"),
            @Mapping(target = "vote", expression = "java(mapVote(vote.getVote()))")
    })
    VoteDto mapVoteToDto(Vote vote);

    default Integer mapVote(VoteType voteType) {
        return voteType.ordinal();
    }

    @Mappings({
            @Mapping(target = "post", ignore = true),
            @Mapping(target = "user", ignore = true),
            @Mapping(target = "vote", expression = "java(inverseMapVote(voteDto.getVote()))")
    })
    Vote mapDtoToVote(VoteDto voteDto);

    default VoteType inverseMapVote(Integer vote) {
        return vote >= 0? VoteType.UPVOTE : VoteType.DOWNVOTE;
    }
}
