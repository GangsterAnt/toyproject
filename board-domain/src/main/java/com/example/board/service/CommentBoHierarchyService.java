package com.example.board.service;

import com.example.board.bo.CommentBo;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommentBoHierarchyService {

    public List<CommentBo> assembleCommentListToHierarchy(List<CommentBo> comments) {
        if (comments == null) {
            return null;
        }

        return collectRootComments(comments);
    }

    private List<CommentBo> collectRootComments(List<CommentBo> comments) {

        //find root comments
        List<CommentBo> rootComments = getRootComments(comments);

        //map comments by id O(n)
        Map<Long, CommentBo> commentMap = generateCommentBoMap(comments);

        //update parent comment's child comments O(n)
        for (CommentBo commentBo : comments) {
            if (commentBo.getParentCommentId() != null) {
                CommentBo parentComment = commentMap.get(commentBo.getParentCommentId());
                if (parentComment != null) {
                    parentComment.getChildCommentBoList().add(commentBo);
                }
            }
        }

        //sort comments by created date
        rootComments.sort(Comparator.comparing(CommentBo::getCreatedAt));
        for (CommentBo commentBo : rootComments) {
            commentBo.getChildCommentBoList().sort(Comparator.comparing(CommentBo::getCreatedAt));
        }

        return rootComments;
    }

    private Map<Long, CommentBo> generateCommentBoMap(List<CommentBo> comments) {
        Map<Long, CommentBo> commentMap = new HashMap<>();
        for (int i = 0; i < comments.size(); i++) {
            CommentBo comment = comments.get(i);
            commentMap.put(comment.getCommentId(), comment);
        }
        return commentMap;
    }

    private List<CommentBo> getRootComments(List<CommentBo> comments) {
        List<CommentBo> rootComments = new ArrayList<>();
        for (CommentBo comment : comments) {
            if (comment.getParentCommentId() == null) {
                rootComments.add(comment);
                comment.setChildCommentBoList(new ArrayList<>());
            }
        }
        return rootComments;
    }
}
