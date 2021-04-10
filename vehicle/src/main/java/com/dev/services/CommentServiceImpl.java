package com.dev.services;

import com.dev.domain.Comment;
import com.dev.domain.Vehicle;
import com.dev.exeptions.AppResponseEntityException;
import com.dev.repository.CommentRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends AbstractCommonServiceImpl<Comment, String, CommentRepository>
        implements CommentService {

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        super("Comment");
        this.commentRepository = commentRepository;
    }


    @Override
    public CommentRepository repository() {
        return commentRepository;
    }

    @Override
    public Comment validate(Comment entity) {
        if (entity.getVehicle() == null) throw new AppResponseEntityException("Vehicle cannot be null");
        if (entity.getContent() == null || entity.getContent().isEmpty())
            throw new AppResponseEntityException("Comment content cannot be null");
        return entity;
    }

    @Override
    public List<Comment> fetchCommentsByVehicle(Vehicle vehicle) {
        return this.commentRepository.findAllByVehicle(vehicle);
    }
}
