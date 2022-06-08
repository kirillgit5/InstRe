package com.kramar.Inst.Auth.Controllers;
import com.kramar.Inst.core.models.user.Gender;
import com.kramar.Inst.core.models.user.UserEntity;
import com.kramar.Inst.network.common.ErrorResponse;
import com.kramar.Inst.network.singIn.request.SignInRequest;
import com.kramar.Inst.network.singIn.response.SignInResponse;
import com.kramar.Inst.network.singUp.request.SignUpRequest;
import com.kramar.Inst.network.singUp.response.SingUpResponse;
import com.kramar.Inst.security.jwt.JwtUtils;
import com.kramar.Inst.services.repositories.RoleRepository;
import com.kramar.Inst.services.repositories.UserRepository;
import com.kramar.Inst.services.userDetails.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.parser.Entity;

@Service
public class AuthModel {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private JwtUtils jwtUtils;

    ResponseEntity<?> handle(SignUpRequest signUpRequest) {
        if(userRepository.existsByLogin(signUpRequest.getLogin())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("userAlreadyExist"));
        }

        if(userRepository.existsByNickname(signUpRequest.getNickname())) {
            return ResponseEntity
                    .badRequest()
                    .body(new ErrorResponse("nickAlreadyUsed"));
        }

        userService.saveUser(
                signUpRequest.getLogin(),
                signUpRequest.getFirstName(),
                signUpRequest.getLastName(),
                signUpRequest.getNickname(),
                signUpRequest.getPassword(),
                Gender.valueOf(signUpRequest.getGender())
        );

        String jwt = generateToken(signUpRequest.getLogin(), signUpRequest.getPassword());
        SingUpResponse response = new SingUpResponse();
        response.setToken(jwt);
        return  ResponseEntity.ok(response);
    }

    ResponseEntity<?> handle(SignInRequest singInRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(singInRequest.getLogin(), singInRequest.getPassword())
        );

        String jwt = generateToken(singInRequest.getLogin(), singInRequest.getPassword());
        SignInResponse response = new SignInResponse();
        response.setToken(jwt);
        return ResponseEntity.ok(response);
    }


    private String generateToken(String login, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(login, password)
        );

        String jwt = jwtUtils.generateJwtToken(authentication);

        return  jwt;
    }
}
