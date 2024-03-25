package com.example.marathon.service;

import com.example.marathon.entity.User;
import com.example.marathon.exceptions.UsernameAlredyExistException;
import com.example.marathon.model.SecurityUser;
import com.example.marathon.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CustomUserDeteilService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDeteilService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        var u = userRepository.findByUsername(username);

       User user = u.orElseThrow(() -> new UsernameNotFoundException("User not found"));


        return new SecurityUser(user);//new SecurityUser is custom class implementing UserDetails
    }


    public void createUser(User user) {
        var u = userRepository.findByUsername(user.getUsername());
        if (u.isPresent()) {
            throw new UsernameAlredyExistException();
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }
}
