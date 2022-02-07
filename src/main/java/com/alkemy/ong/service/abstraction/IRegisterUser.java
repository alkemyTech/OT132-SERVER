package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.UserAlreadyExistException;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.UserResponse;


public interface IRegisterUser {

  UserResponse register(UserRegisterRequest dtoRequest) throws UserAlreadyExistException;

}
