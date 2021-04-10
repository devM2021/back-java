package com.dev.api;

import com.dev.domain.LocalUser;
import com.dev.domain.UserRoleDto;
import com.dev.exeptions.AppResponseEntityException;
import com.dev.services.interfaces.UserRoleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("users")
public class Users {

    private final UserRoleService userRoleService;

    public Users(UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }

    @GetMapping("list")
    public ResponseEntity<List<LocalUser>> list() {
        return new ResponseEntity<>(userRoleService.fetchAllRecord(), HttpStatus.ACCEPTED);
    }

    @PostMapping("save-user")
    public ResponseEntity<LocalUser> saveUser(@RequestBody UserRoleDto userRoleDto) {
        LocalUser localUser = new LocalUser();
        localUser.setId(userRoleDto.getId());
        localUser.setUsername(userRoleDto.getUsername());
        localUser.setEmail(userRoleDto.getEmail());
        if (!userRoleService.comparePassword(userRoleDto.getPassword(), userRoleDto.getConfirmPassword()))
            throw new AppResponseEntityException("Password not match");
        localUser.setPassword(userRoleDto.getPassword());
        return new ResponseEntity<>(userRoleService.saveUser(localUser, userRoleDto.getRolesId()), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<LocalUser> listById(@PathVariable String id) {
        return new ResponseEntity<>(userRoleService.fetchOneRecordById(id), HttpStatus.ACCEPTED);
    }


    @GetMapping("{username}")
    public ResponseEntity<LocalUser> list(@PathVariable String username) {
        return new ResponseEntity<>(userRoleService.fetchOneByUsername(username), HttpStatus.ACCEPTED);
    }

}
