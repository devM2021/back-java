package com.dev.api;

import com.dev.domain.Roles;
import com.dev.services.interfaces.RoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("roles")
public class UserRoles {
    private final RoleService roleService;

    public UserRoles(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping("list")
    public ResponseEntity<List<Roles>> list() {
        return new ResponseEntity<>(roleService.fetchAllRecord(), HttpStatus.ACCEPTED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Roles> findOne(@PathVariable String id) {
        return new ResponseEntity<>(roleService.fetchOneRecordById(id),
                HttpStatus.ACCEPTED);
    }

    @PostMapping("save-role")
    public ResponseEntity<Roles> saveRoles(@RequestBody String roleName) {
        return new ResponseEntity<>(roleService.createOrUpdate(Roles.builder().name(roleName).build()), HttpStatus.CREATED);
    }
}
