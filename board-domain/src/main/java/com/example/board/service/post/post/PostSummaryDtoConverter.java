package com.example.board.service.post.post;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class PostSummaryDtoConverter {

//    public List<PostSummaryDto> convertToDtoList(List<PostSummaryBo> postSummaryBoList) {
//        return Optional.ofNullable(postSummaryBoList)
//                .stream()
//                .flatMap(List::stream)
//                .map(this::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    public PostSummaryDto convertToDto(PostSummaryBo postSummaryBo) {
//        return PostSummaryDto.builder()
//                .postId(postSummaryBo.getPostId())
//                .title(postSummaryBo.getTitle())
//                .ownerMemberId(postSummaryBo.getOwnerMemberId())
//                .likes(postSummaryBo.getLikes())
//                .dislikes(postSummaryBo.getDislikes())
//                .createdAt(postSummaryBo.getCreatedAt())
//                .build();
//    }
//
//    public List<PostSummaryBo> convertToBoList(List<PostSummaryDto> postSummaryDtoList) {
//        return Optional.ofNullable(postSummaryDtoList)
//                .stream()
//                .flatMap(List::stream)
//                .map(this::convertToBo)
//                .collect(Collectors.toList());
//    }
//
//    public PostSummaryBo convertToBo(PostSummaryDto postSummaryDto) {
//        return PostSummaryBo.builder()
//                .postId(postSummaryDto.getPostId())
//                .title(postSummaryDto.getTitle())
//                .ownerMemberId(postSummaryDto.getOwnerMemberId())
//                .likes(postSummaryDto.getLikes())
//                .dislikes(postSummaryDto.getDislikes())
//                .createdAt(postSummaryDto.getCreatedAt())
//                .build();
//    }
}
