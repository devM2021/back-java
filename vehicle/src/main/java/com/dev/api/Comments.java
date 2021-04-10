package com.dev.api;


import com.dev.domain.Comment;
import com.dev.domain.LocalUser;
import com.dev.domain.Vehicle;
import com.dev.dto.CommentInputDto;
import com.dev.services.CommentService;
import com.dev.services.VehicleService;
import com.dev.services.interfaces.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("comments")
@Slf4j
public class Comments {

    private final CommentService commentService;

    private final UserRoleService userRoleService;

    private final VehicleService vehicleService;

    public Comments(CommentService commentService, UserRoleService userRoleService, VehicleService vehicleService) {
        this.commentService = commentService;
        this.userRoleService = userRoleService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("list")
    public ResponseEntity<List<Comment>> list(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            return new ResponseEntity<>(commentService.fetchAllRecord(), HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.ACCEPTED);
        }

    }

    @GetMapping("list-by-vehicleId/{id}")
    public ResponseEntity<List<Comment>> listByVehicle(Authentication authentication, @PathVariable String id) {
        if (authentication != null && authentication.isAuthenticated()) {
            return new ResponseEntity<>(commentService.fetchCommentsByVehicle(this.vehicleService.fetchOneRecordById(id)),
                    HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.ACCEPTED);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<Comment> findOne(@PathVariable String id) {
        return new ResponseEntity<>(commentService.fetchOneRecordById(id),
                HttpStatus.ACCEPTED);

    }

    @PostMapping("save-comment")
    public ResponseEntity<Comment> save(@RequestBody CommentInputDto commentDto, Authentication authentication) {
        LocalUser user = null;
        Vehicle vehicle = null;
        if (authentication != null && authentication.isAuthenticated()) {
            user = this.userRoleService.fetchOneByUsername(authentication.getName());
        }

        if (commentDto.getVehicleId() != null && !commentDto.getVehicleId().isEmpty()) {
            vehicle = vehicleService.fetchOneRecordById(commentDto.getVehicleId());
        }

        Comment comment = new Comment();
        comment.setCommentDate(LocalDate.now());
        comment.setContent(commentDto.getContent());
        comment.setVehicle(vehicle);
        comment.setLocalUser(user);

        return new ResponseEntity<>(commentService.createOrUpdate(comment),
                HttpStatus.ACCEPTED);

    }
}
