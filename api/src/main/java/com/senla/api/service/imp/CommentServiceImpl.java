package com.senla.api.service.imp;

import com.senla.api.dao.CommentDao;
import com.senla.api.model.Comment;
import com.senla.api.service.CommentService;
import com.senla.di.annotation.Autowired;
import com.senla.di.annotation.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public Optional<Comment> createComment(Comment comment) {
        return commentDao.create(comment);
    }

    @Override
    public Optional<Comment> getCommentById(UUID id) {
        return commentDao.getById(id);
    }

    @Override
    public List<Comment> getAllComments() {
        return commentDao.getAll();
    }

    @Override
    public Optional<Comment> updateComment(Comment comment, UUID id) {
//        Comment updatedComment = Comment.builder()
//                .content(comment.getContent())
//                .updatedDate(LocalDateTime.now())
//                .build();
//
//        return commentDao.update(updatedComment, id);
        return commentDao.update(comment, id);
    }

    @Override
    public Optional<Comment> deleteComment(UUID id) {
        return commentDao.delete(id);
    }
}
