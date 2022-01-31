package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.model.request.AuthenticationRequest;
import com.alkemy.ong.model.response.AuthenticationResponse;

public interface ILogin {

  AuthenticationResponse login(AuthenticationRequest authenticationRequest)
      throws InvalidCredentialsException;

}
