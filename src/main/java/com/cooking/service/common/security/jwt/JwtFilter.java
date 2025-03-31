package com.cooking.service.common.security.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

import static com.cooking.service.common.security.SecurityConstants.BEARER;

@Slf4j
@Service
public class JwtFilter extends OncePerRequestFilter {

    private final JwtService jwtService;

    public JwtFilter(JwtService jwtService) {
        super();
        this.jwtService = jwtService;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        extractToken(request).ifPresent(token -> {
            var claims = jwtService.extractClaims(token);
            if (claims != null) {
                var authentication = jwtService.getAuthentication(claims);
                SecurityContextHolder.getContext().setAuthentication(authentication);

                try {
                    filterChain.doFilter(request, response);
                } catch (ServletException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        filterChain.doFilter(request, response);
    }

    private Optional<String> extractToken(HttpServletRequest request) {
        var authorization = request.getHeader("Authorization");
        if (authorization != null && authorization.startsWith(BEARER)) {
            return Optional.of(authorization.substring(7));
        }
        return Optional.empty();
    }
}
