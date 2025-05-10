package com.ganeshban.smsserver.service.impl;


import com.ganeshban.smsserver.entity.UserEntity;
import com.ganeshban.smsserver.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.ganeshban.smsserver.utils.Constants.isActive;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity appUser = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("username not found for user " + username));


        return org.springframework.security.core.userdetails.User
                .withUsername(username)
                .password("XXXX")
//                .authorities(appUser.getUserRoles())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .disabled(isActive(appUser))
                .build();
    }

}