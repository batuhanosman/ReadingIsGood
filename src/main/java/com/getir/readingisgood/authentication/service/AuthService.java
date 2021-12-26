package com.getir.readingisgood.authentication.service;

import com.getir.readingisgood.authentication.model.request.SignUpRequest;
import com.getir.readingisgood.authentication.model.request.SigninRequest;
import com.getir.readingisgood.authentication.model.response.JwtResponse;
import com.getir.readingisgood.entity.User;

public interface AuthService {

    JwtResponse signIn(SigninRequest request);
    User signUp(SignUpRequest request);
}
