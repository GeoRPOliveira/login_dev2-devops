package com.example.apisecuritybasic.services;

import com.example.apisecuritybasic.models.Users;
import com.example.apisecuritybasic.repository.UsersRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class CustomUserDatailsServices implements UserDetailsService {
    private final UsersRepository usersRepository;
    public CustomUserDatailsServices(UsersRepository usersRepository){
        this.usersRepository = usersRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não existe"));
        return new org.springframework.security.core.userdetails.User(
                users.getUsername(),
                users.getPassword(),
                users.isEnabled(),
                true, // accountNotExpired
                true, // credentialsNonExpired
                true, // accountNonLocked
                users.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                        .collect(Collectors.toList())
        );
    }

}
