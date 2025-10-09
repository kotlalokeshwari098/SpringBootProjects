package com.javaspring.server.security.services;

import com.javaspring.server.model.User;
import com.javaspring.server.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        //Fetch user from DB
        User user=userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException("user not found"));

        //Convert to Spring Security format
    return UserDetailsImpl.build(user);
    }
}
