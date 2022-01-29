package com.alkemy.ong.service;

import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.UserRegisterResponse;
import com.alkemy.ong.repository.IRoleRepository;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IRegisterUserService;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService implements IRegisterUserService {

  @Autowired
  private IUserRepository iUserRepository;
  @Autowired
  private IRoleRepository iRoleRepository;
  @Autowired
  private UserMapper userMapper;

  @Override
  public UserRegisterResponse dataRegister(UserRegisterRequest dtoRequest)
      throws ExternalServiceException {

    if (iUserRepository.findByEmail(dtoRequest.getEmail()) == null) {
      User user = userMapper.registerRequestMap(dtoRequest, new BCryptPasswordEncoder().encode(dtoRequest.getPassword()));
      iUserRepository.save(user);
      ///user.setRoles(new ArrayList<>(iRoleRepository.));
      return userMapper.registerResponseMap(user);

    }else{
      throw new ExternalServiceException("This e-mail is already in use");

    }



  }
}
