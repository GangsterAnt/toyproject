package com.example.board.service;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommentHierarchyService {

    public List<CommentBo> assembleCommentListToHierarchy(List<CommentBo> comments) {
        if (comments == null) {
            return null;
        }

        return collectRootComments(comments);
    }

    private List<CommentBo> collectRootComments(List<CommentBo> comments) {
        List<CommentBo> rootComments = new ArrayList<>();
        Map<Long, CommentBo> commentMap = new HashMap<>();

        //find root comments
        for (int i = 0; i < comments.size(); i++) {
            CommentBo comment = comments.get(i);
            if (comment.getParentCommentId() == null) {
                rootComments.add(comment);
            }
        }

        //map comments by id O(n)
        for (int i = 0; i < comments.size(); i++) {
            CommentBo comment = comments.get(i);
            commentMap.put(comment.getCommentId(), comment);
        }

        //make hierarchy
        for (CommentBo commentBo : comments) {
            if (commentBo.getParentCommentId() != null) {
                CommentBo parentComment = commentMap.get(commentBo.getParentCommentId());
                if (parentComment != null) {
                    parentComment.setChildComment(commentBo);
                    commentBo.setParentComment(parentComment);
                }
            }
        }

        //sort root comments by created date
        rootComments.sort(Comparator.comparing(CommentBo::getCreatedAt));

        return rootComments;
    }
}
