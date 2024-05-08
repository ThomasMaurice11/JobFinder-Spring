package com.swSecurity.swSecurity.auth;

import com.swSecurity.swSecurity.config.JwtService;
import com.swSecurity.swSecurity.user.Role;
import com.swSecurity.swSecurity.user.User;
import com.swSecurity.swSecurity.user.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.security.core.AuthenticationException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request)
    {
        var user = User.builder()
                .firstname(request.getFirstName())
                .lastname(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))//encode it before store in database
                .role(Role.User)
                .build();
        userRepo.save(user);
        var jwt = jwtService.generateToken(user);
        AuthenticationResponse response = AuthenticationResponse.builder().token(jwt).build();

        // Return the AuthenticationResponse
        return response;
    }

    public AuthenticationResponse Authenticate(AuthenticationRequest request)
    {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepo.findByEmail(request.getEmail()).orElseThrow();
        var jwt = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwt).build();
    }
}
