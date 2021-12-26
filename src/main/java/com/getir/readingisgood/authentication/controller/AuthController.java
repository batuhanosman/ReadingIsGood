package com.getir.readingisgood.authentication.controller;

import com.getir.readingisgood.authentication.jwt.JwtUtils;
import com.getir.readingisgood.authentication.model.request.SignUpRequest;
import com.getir.readingisgood.authentication.model.request.SigninRequest;
import com.getir.readingisgood.authentication.model.response.JwtResponse;
import com.getir.readingisgood.authentication.service.AuthService;
import com.getir.readingisgood.constants.ApiEndPoints;
import com.getir.readingisgood.entity.User;
import com.getir.readingisgood.model.response.BaseApiResponse;
import com.getir.readingisgood.repository.RoleRepository;
import com.getir.readingisgood.repository.UserRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = ApiEndPoints.AUTH_API, produces = ApiEndPoints.RESPONSE_CONTENT_TYPE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
@Api
@SwaggerDefinition(tags = {@Tag(name = "reading-is-good-auth-api", description = "Authorization Api")})
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public BaseApiResponse<JwtResponse> authenticateUser(@Valid @RequestBody SigninRequest signinRequest) {
        return new BaseApiResponse(authService.signIn(signinRequest));
    }

    @PostMapping("/signup")
    public BaseApiResponse<User> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        return new BaseApiResponse(authService.signUp(signUpRequest));
    }
}
