package com.alkemy.ong.service;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.UserAlreadyExistException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.repository.IRoleRepository;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IRegisterUser;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegisterService implements IRegisterUser {

  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private IRoleRepository roleRepository;
  @Autowired
  private UserMapper userMapper;

  @Override
  public UserResponse register(UserRegisterRequest userRegisterRequest)
      throws UserAlreadyExistException {
    if (userRepository.findByEmail(userRegisterRequest.getEmail()) != null) {
      throw new UserAlreadyExistException();
    }

    User user = userMapper.map(userRegisterRequest,
        new BCryptPasswordEncoder().encode(userRegisterRequest.getPassword()));
    user.setRoles(List.of(roleRepository.findByName(RoleType.USER.getFullRoleName())));
    return userMapper.map(userRepository.save(user));
  }

}
