package com.feljtech.istudybucket.business.service.impl;

import com.feljtech.istudybucket.data.entity.User;
import com.feljtech.istudybucket.data.enums.UserRole;
import com.feljtech.istudybucket.data.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
@Slf4j
@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info(String.format("Loading user by username: %s", username));
        Optional<User> userOptional = userRepository.findByUsername(username);
        User foundUser = userOptional.orElseThrow(() -> {
            log.warn(String.format("User with username '%s' not found", username));
            throw new UsernameNotFoundException("No user for the username: ".concat(username));
        });

        log.info("Loading user with username: ".concat(username));

        return new org.springframework.security.core.userdetails.User(
                foundUser.getUsername(), // username
                foundUser.getPassword(), // user pass
                foundUser.getUserVerified(), // user verified
                Boolean.TRUE, // account non expired
                Boolean.TRUE, // credentials non expired
                Boolean.TRUE, // account non-locked
                getAuthorities(foundUser.getUserRole())
        );
    }

    private Collection<? extends GrantedAuthority> getAuthorities(UserRole userRole) {
        log.info("Getting user's granted authority");
        return Collections.singletonList(new SimpleGrantedAuthority(userRole.toString()));
    }
}
