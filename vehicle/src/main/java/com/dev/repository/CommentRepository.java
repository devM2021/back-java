package com.dev.repository;

import com.dev.domain.Comment;
import com.dev.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, String> {

    List<Comment> findAllByVehicle(Vehicle vehicle);

}
