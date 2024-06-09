package com.project.backend.auth;

import com.project.backend.entity.Account;
import com.project.backend.entity.Role;
import com.project.backend.repository.AccountRepository;
import com.project.backend.repository.RoleRepository;
import com.project.backend.service.JwtService;
import com.project.backend.utilies.RoleName;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final AccountRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleRepository roleRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        var user = Account.builder().username(request.getUsername()).password(
                passwordEncoder.encode(request.getPassword())).email(request.getEmail()).build();
        Set<String> rolesString = request.getRoles();
        Set<Role> roles = new HashSet<>();
        if (rolesString == null) {
            Role userRole = roleRepository.findByName(RoleName.USER).orElseThrow(
                    () -> new RuntimeException("Role not found"));
            roles.add(userRole);
        } else {
            rolesString.forEach(role -> {

//

                switch (role) {
                    case "ADMIN":
                        Role adminRole = roleRepository.findByName(RoleName.ADMIN).orElseThrow(
                                () -> new RuntimeException("Role not found"));
                        roles.add(adminRole);
                    case "STAFF":
                        Role staffRole = roleRepository.findByName(RoleName.STAFF).orElseThrow(
                                () -> new RuntimeException("Role not found"));
                        roles.add(staffRole);
                    default:
                        Role userRole = roleRepository.findByName(RoleName.USER).orElseThrow(
                                () -> new RuntimeException("Role not found"));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        System.out.println(roles);
        repository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }


    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        var user  = repository.findByUsername(request.getUsername()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }
}

