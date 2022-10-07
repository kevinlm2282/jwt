package com.example.seguridadjwt.dto.entitityMapper;

import com.example.seguridadjwt.dao.UserEntity;
import com.example.seguridadjwt.dto.UserPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserPrincipal userToPrincipal(UserEntity user){
        UserPrincipal userPrincipal = new UserPrincipal();
        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+ role.getName()))
                .collect(Collectors.toList());
        userPrincipal.setUsername(user.getUsername());
        userPrincipal.setPassword(user.getPassword());
        userPrincipal.setAuthorities(authorities);
        userPrincipal.setEnabled(user.isEnabled());
        return userPrincipal;
    }
}
