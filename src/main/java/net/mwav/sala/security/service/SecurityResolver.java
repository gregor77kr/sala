package net.mwav.sala.security.service;

import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class SecurityResolver {

    public static Optional<String> getCustomerId() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            log.debug("Security Context에 인증 정보가 없습니다.");
            return Optional.empty();
        }

        String customerId = null;
        if (authentication.getPrincipal() instanceof UserDetails) {
            UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
            customerId = springSecurityUser.getUsername();
        } else if (authentication.getPrincipal() instanceof String) {
            customerId = (String) authentication.getPrincipal();
        }

        return Optional.ofNullable(customerId);
    }
}
