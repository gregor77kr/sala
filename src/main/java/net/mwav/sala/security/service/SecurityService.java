package net.mwav.sala.security.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import net.mwav.sala.common.util.HashUtils;
import net.mwav.sala.security.dto.CustomerDetails;

@Service
@RequiredArgsConstructor
public class SecurityService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    public Authentication authenticate(String customerId, String password) throws Exception {

        // create UsernamePasswordAuthenticationToken instance using
        // AuthenticationRequest(id and password)
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
        	customerId, password);

        // create Authentication instance using UsernamePasswordAuthenticationToken
        // during creating this instance, UserDetailSerivceImpl.loadUserByUsername is called
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // compare password
        CustomerDetails customerDetails = (CustomerDetails) authentication.getPrincipal();
        String expectedPassword = customerDetails.getPassword();
        password = HashUtils.digest("SHA-256", password + customerDetails.getSalt());

        if (!expectedPassword.equals(password)) {
            throw new BadCredentialsException("아이디 혹은 비밀번호를 확인해주세요.");
        }

        // save Authentication in security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }
    
}
