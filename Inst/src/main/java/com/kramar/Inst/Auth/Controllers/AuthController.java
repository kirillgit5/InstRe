package com.kramar.Inst.Auth.Controllers;

import com.kramar.Inst.network.singIn.request.SignInRequest;
import com.kramar.Inst.network.singUp.request.SignUpRequest;
import com.kramar.Inst.security.jwt.JwtUtils;
import com.kramar.Inst.services.repositories.UserRepository;
import com.kramar.Inst.services.userDetails.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthModel model;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserDetailsServiceImpl userService;

    @Autowired
    private JwtUtils jwtUtils;

    @PostMapping("/signUp")
    public ResponseEntity<?> signUp(@Valid @RequestBody SignUpRequest signUpRequest) {
        return model.handle(signUpRequest);
    }

    @PostMapping("/signIn")
    public  ResponseEntity<?> signIn(@Valid @RequestBody SignInRequest signInRequest) {
        return model.handle(signInRequest);
    }
}
