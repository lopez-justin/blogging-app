package com.justinlopez.bloggingapp.config.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Fetch token from request
        Optional<String> tokenFromRequest = getTokenFromRequest(request);

        // Validate token -> JwtUtils
        tokenFromRequest.ifPresent(token -> {
            if (JwtUtils.validateToken(token)) {
                var usernameOptional = JwtUtils.getUsernameFromToken(token);

                usernameOptional.ifPresent(username -> {
                    // Fetch user details with the help of username
                    var userDetails = userDetailsService.loadUserByUsername(username);

                    // Create an authentication object
                    var authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    // Set the username in the security context
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                });
            }
        });

        // Continue the filter chain
        filterChain.doFilter(request, response);

    }

    private Optional<String> getTokenFromRequest(HttpServletRequest request) {
        // Extract token from the request
        String bearerToken = request.getHeader("Authorization");

        // Bearer <JWT TOKEN>
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return Optional.of(bearerToken.substring(7));
        }
        return Optional.empty();
    }

}
