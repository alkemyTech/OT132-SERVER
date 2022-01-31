package com.alkemy.ong.service;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.UserRegisterException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.UserRegisterResponse;
import com.alkemy.ong.repository.IRoleRepository;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IRegisterUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserRegisterService implements IRegisterUserService {

  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private IRoleRepository roleRepository;
  @Autowired
  private UserMapper userMapper;

  @Override
  public UserRegisterResponse dataRegister(UserRegisterRequest dtoRequest)
      throws UserRegisterException {

    if (userRepository.findByEmail(dtoRequest.getEmail()) == null) {
      User user = userMapper.registerRequestMap(dtoRequest,
          new BCryptPasswordEncoder().encode(dtoRequest.getPassword()));
      user.setRoles(roleRepository.findByName(RoleType.USER.name()));
      userRepository.save(user);
      return userMapper.registerResponseMap(user);

    } else {
      throw new UserRegisterException("This e-mail is already in use");

    }
  }
}
