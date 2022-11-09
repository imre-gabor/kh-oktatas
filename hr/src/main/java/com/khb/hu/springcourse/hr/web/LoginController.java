package com.khb.hu.springcourse.hr.web;

import com.khb.hu.springcourse.hr.api.LoginControllerApi;
import com.khb.hu.springcourse.hr.api.model.LoginDto;
import com.khb.hu.springcourse.hr.security.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController implements LoginControllerApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Override
    public ResponseEntity<String> login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(), loginDto.getPassword()));

        return ResponseEntity.ok(jwtService.createJwt((UserDetails) authentication.getPrincipal()));

    }
}
