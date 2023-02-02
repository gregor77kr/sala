package net.mwav.sala.security.service;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import lombok.experimental.UtilityClass;

@UtilityClass
public class SecurityResolver {

    public static Long getCustomerId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (authentication == null) ? -1 : Long.valueOf(authentication.getName());
    }

    public boolean authorize(long customerId) {
        if (getCustomerId() != customerId) {
            throw new AccessDeniedException(null);
        }

        return true;
    }
}
