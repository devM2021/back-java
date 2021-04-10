package com.dev.services.interfaces;

import com.dev.domain.LocalUserDetails;

public interface SecurityService {
    LocalUserDetails currentUser();
}
