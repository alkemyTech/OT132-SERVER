package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.UserRegisterResponse;


public interface IRegisterUserService {

  UserRegisterResponse dataRegister(UserRegisterRequest dtoRequest) throws ExternalServiceException;

}
