package com.feljtech.istudybucket.service.impl;

import com.feljtech.istudybucket.entity.User;
import com.feljtech.istudybucket.enums.UserRole;
import com.feljtech.istudybucket.repository.UserRepository;
import lombok.AllArgsConstructor;
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

@Service
@AllArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User foundUser = userOptional.orElseThrow(() -> {
            throw new UsernameNotFoundException("No user for the username: ".concat(username));
        });
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
        return Collections.singletonList(new SimpleGrantedAuthority(userRole.toString()));
    }
}
