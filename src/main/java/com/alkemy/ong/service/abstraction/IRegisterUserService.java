package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.UserRegisterException;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.UserResponse;


public interface IRegisterUserService {

  UserResponse register(UserRegisterRequest dtoRequest) throws UserRegisterException;

}
