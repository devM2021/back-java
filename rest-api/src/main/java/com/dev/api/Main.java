package com.dev.api;

import com.dev.domain.LocalUserDetails;
import com.dev.dto.JwtLoginData;
import com.dev.dto.JwtResponse;
import com.dev.exeptions.AppResponseEntityException;
import com.dev.security.JwtTokenUtil;
import com.dev.services.interfaces.SecurityService;
import com.dev.services.interfaces.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@Slf4j
@CrossOrigin(origins = "*")
public class Main {

    private final SecurityService securityService;

    private final AuthenticationManager authenticationManager;

    private final JwtTokenUtil jwtTokenUtil;

    private final UserRoleService userDetailsService;

    public Main(SecurityService securityService, AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, UserRoleService userDetailsService) {
        this.securityService = securityService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }

    @GetMapping("/")
    @ResponseBody
    public ResponseEntity<LocalUserDetails> accueil() {
        return new ResponseEntity<>(securityService.currentUser(), HttpStatus.ACCEPTED);
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtLoginData request)
            throws AppResponseEntityException {
        authenticate(request.getUsername(), request.getPassword());

        final LocalUserDetails userDetails = (LocalUserDetails) userDetailsService.loadUserByUsername(request.getUsername());

        JwtResponse response = JwtResponse.builder()
                .token(jwtTokenUtil.generateToken(userDetails))
                .username(userDetails.getUsername())
                .roles(Collections.singletonList(userDetails.getAuthorities().toString())).build();
        return ResponseEntity.ok(response);

    }

    private void authenticate(String username, String password) throws AppResponseEntityException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            throw new AppResponseEntityException("INVALID_CREDENTIALS", e);
        } catch (Exception e) {
            throw new AppResponseEntityException(e.getMessage());
        }
    }
}
