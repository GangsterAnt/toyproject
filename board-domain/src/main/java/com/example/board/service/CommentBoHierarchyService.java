package com.example.board.service;

import com.example.board.domain.Comment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CommentBoHierarchyService {

    @Deprecated
    public List<Comment> assembleCommentListToHierarchy(List<Comment> comments) {
        if (comments == null) {
            return null;
        }

        return collectRootComments(comments);
    }

    private List<Comment> collectRootComments(List<Comment> comments) {

        //find root comments
        List<Comment> rootComments = getRootComments(comments);

        //map comments by id O(n)
        Map<Long, Comment> commentMap = generateCommentBoMap(comments);

        //update parent comment's child comments O(n)
        for (Comment comment : comments) {
            if (comment.getParentCommentId() != null) {
                Comment parentComment = commentMap.get(comment.getParentCommentId());
                if (parentComment != null) {
                    parentComment.getChildCommentList().add(comment);
                }
            }
        }

        //sort comments by created date
        rootComments.sort(Comparator.comparing(Comment::getCreatedAt));
        for (Comment comment : rootComments) {
            comment.getChildCommentList().sort(Comparator.comparing(Comment::getCreatedAt));
        }

        return rootComments;
    }

    private Map<Long, Comment> generateCommentBoMap(List<Comment> comments) {
        Map<Long, Comment> commentMap = new HashMap<>();
        for (int i = 0; i < comments.size(); i++) {
            Comment comment = comments.get(i);
            commentMap.put(comment.getCommentId(), comment);
        }
        return commentMap;
    }

    private List<Comment> getRootComments(List<Comment> comments) {
        List<Comment> rootComments = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getParentCommentId() == null) {
                rootComments.add(comment);
                comment.setChildCommentList(new ArrayList<>());
            }
        }
        return rootComments;
    }
}
