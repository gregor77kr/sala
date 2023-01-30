package net.mwav.sala.authentication.jwt;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

@Component
public class JwtWebResolver {

    @Value("${jwt.access-token-name}")
    private String accessTokenName;

    @Value("${jwt.refresh-token-validity}")
    private long refreshTokenValidity;

    @Value("${jwt.refresh-token-name}")
    private String refreshTokenName;

    @Value("${jwt.refresh-token-url}")
    private String refreshTokenUrl;

    @Value("${jwt.domain}")
    private String domain;

    public String createRefreshCookie(String token) {
        return createCookieString(this.refreshTokenName, token, this.refreshTokenValidity / 1000);
    }

    private String createCookieString(String name, String token, long validity) {
        ResponseCookie responseCookie = ResponseCookie.from(name, token)
                .httpOnly(true)
                .sameSite("Lax")
                .domain(domain)
                .secure(false)
                .maxAge(validity)
                .build();
        return responseCookie.toString();
    }

    public String getAccessTokenInHeader(HttpServletRequest request) {
        String bearerToken = request.getHeader(accessTokenName);

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public String getRefreshTokenInCookie(HttpServletRequest request) {
        Cookie cookie = WebUtils.getCookie(request, refreshTokenName);
        return (cookie == null) ? null : cookie.getValue();
    }
}
