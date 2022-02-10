package com.alkemy.ong.service;

import com.alkemy.ong.common.JwtUtils;
import com.alkemy.ong.common.mail.EmailUtils;
import com.alkemy.ong.common.mail.template.ContactUs;
import com.alkemy.ong.common.mail.template.RegisterEmailTemplate;
import com.alkemy.ong.common.mail.template.WelcomeEmailTemplate;
import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.exception.UserAlreadyExistException;
import com.alkemy.ong.mapper.UserMapper;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.UserResponse;
import com.alkemy.ong.repository.IRoleRepository;
import com.alkemy.ong.repository.IUserRepository;
import com.alkemy.ong.service.abstraction.IRegisterUser;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class RegisterService implements IRegisterUser {

  @Autowired
  private IUserRepository userRepository;
  @Autowired
  private IRoleRepository roleRepository;
  @Autowired
  private UserMapper userMapper;
  @Autowired
  private BCryptPasswordEncoder passwordEncoder;
  @Autowired
  private JwtUtils jwtUtil;
  @Autowired
  private EmailUtils emailUtils;

  @Value("${organization.name.contact}")
  private String contactUsName;
  @Value("${organization.address.contact}")
  private String contactUsAddress;
  @Value("${organization.phone.contact}")
  private int contactUsPhone;

  @Override
  public UserResponse register(UserRegisterRequest userRegisterRequest)
      throws UserAlreadyExistException {
    if (userRepository.findByEmail(userRegisterRequest.getEmail()) != null) {
      throw new UserAlreadyExistException();
    }
    User user = userMapper.map(userRegisterRequest,
        passwordEncoder.encode(userRegisterRequest.getPassword()));
    user.setRoles(List.of(roleRepository.findByName(RoleType.USER.getFullRoleName())));
    UserResponse userResponse = userMapper.map(userRepository.save(user));

    ContactUs contactUs = new ContactUs(contactUsName, contactUsAddress, contactUsPhone);
    RegisterEmailTemplate template = new RegisterEmailTemplate(contactUs,
        userRegisterRequest.getEmail());
    try {
      emailUtils.send(template);
    } catch (ExternalServiceException e) {
      log.error(e.getMessage());
    }
    userResponse.setToken(jwtUtil.generateToken(user));
    return userResponse;
  }

}
