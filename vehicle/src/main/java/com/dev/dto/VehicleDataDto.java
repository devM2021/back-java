package com.dev.dto;

import com.dev.domain.UserRoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDataDto {

    private String id;

    private String name;

    private String description;

    private List<CommentDataDto> comments;

    private UserRoleDto users;
}
