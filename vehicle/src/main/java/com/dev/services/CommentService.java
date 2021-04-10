package com.dev.services;

import com.dev.domain.Comment;
import com.dev.domain.Vehicle;

import java.util.List;

public interface CommentService extends CommonService<Comment, String> {

    List<Comment> fetchCommentsByVehicle(Vehicle vehicle);
}
