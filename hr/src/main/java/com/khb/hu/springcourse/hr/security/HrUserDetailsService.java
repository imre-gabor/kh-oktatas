package com.khb.hu.springcourse.hr.security;

import com.khb.hu.springcourse.hr.model.HrUser;
import com.khb.hu.springcourse.hr.repository.HrUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class HrUserDetailsService implements UserDetailsService {

    @Autowired
    private HrUserRepository hrUserRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        HrUser hrUser = hrUserRepository.findById(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return new User(username, hrUser.getPassword(), Arrays.asList(new SimpleGrantedAuthority("CREATE_EMP")));
    }
}
