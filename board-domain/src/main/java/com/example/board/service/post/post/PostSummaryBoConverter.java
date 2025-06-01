package com.example.board.service.post.post;

import org.springframework.stereotype.Component;

@Component
public class PostSummaryBoConverter {

//    public List<PostSummaryBo> convertToBoList(List<Post> postList) {
//        return Optional.ofNullable(postList)
//                .stream()
//                .flatMap(List::stream)
//                .map(this::convertToBo)
//                .collect(Collectors.toList());
//    }
//
//    public PostSummaryBo convertToBo(Post post) {
//        return PostSummaryBo.builder()
//                .postId(post.getPostId())
//                .title(post.getTitle())
//                .ownerMemberId(post.getOwnerMemberId())
//                .likes(post.getLikes())
//                .dislikes(post.getDislikes())
//                .createdAt(post.getCreatedAt())
//                //.commentCount(post.getCommentCount()) //TODO. should we create new colum in post table?
//                .build();
//    }
}
