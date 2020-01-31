package com.sg.hellosecurity.service;

import com.sg.hellosecurity.dao.UserDao;
import com.sg.hellosecurity.entities.Role;
import com.sg.hellosecurity.entities.User;
import java.util.HashSet;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @date Friday January 31, 2020
 * @author garrettbecker
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserDao users;
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = users.getUserByUsername(username);
        
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for(Role role : user.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
    
}
