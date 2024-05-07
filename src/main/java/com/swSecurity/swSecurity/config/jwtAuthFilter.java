package com.swSecurity.swSecurity.config;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
@Component
@RequiredArgsConstructor

//related to filters on token ...
public class jwtAuthFilter extends OncePerRequestFilter {
    private JwtService jwtService;
    private final UserDetailsService userDetailsServices;

    @Override
    protected void doFilterInternal(
            @NonNull HttpServletRequest request,
            @NonNull HttpServletResponse response,
            @NonNull FilterChain filterChain)
            throws ServletException, IOException {

        //when send request token exist in authoheader of request
        final String authHeader = request.getHeader("Authorization");
        final String jwt;
        final String userEmail;
        // 1-: token is exist
        //if header of request is empty or header not start with "bearer" keyword!
        //bearer followed by token if bearer not found so token not exist!
        if(authHeader == null || !authHeader.startsWith("Bearer"))
        {
            //accept or deny access to that resource
            filterChain.doFilter(request, response);
            //no other check
            return;
        }
        jwt = authHeader.substring(7); //extract token .. start after bearer keyword


        //2-:extract email from token and use it to fetch data from database to check user exist or not
        userEmail = jwtService.extractEmail(jwt);
        if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null)
        {
            UserDetails userDetails = this.userDetailsServices.loadUserByUsername(userEmail);
            if (jwtService.IsTokenValid(jwt, userDetails)) //token exist,extract email,validation,expires ends here
            {
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities()
                );
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                // after authenticated set connected user in SecurityContextHolder
                SecurityContextHolder.getContext().setAuthentication(authToken);

            }

        }
        filterChain.doFilter(request, response);

    }
}
