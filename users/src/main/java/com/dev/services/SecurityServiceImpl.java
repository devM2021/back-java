package com.dev.services;

import com.dev.domain.LocalUserDetails;
import com.dev.services.interfaces.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * The implementation  of {@link SecurityService}
 */
@Service
@Slf4j
public class SecurityServiceImpl implements SecurityService {

    @Override
    public LocalUserDetails currentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getPrincipal() instanceof LocalUserDetails
                ? (LocalUserDetails) authentication.getPrincipal() : null;
    }
}
