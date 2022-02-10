package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.UpdateUserDetailsRequest;
import com.alkemy.ong.model.request.UserRegisterRequest;
import com.alkemy.ong.model.response.UserResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserResponse map(User user) {
    UserResponse userResponse = new UserResponse();
    userResponse.setFirstName(user.getFirstName());
    userResponse.setLastName(user.getLastName());
    userResponse.setEmail(user.getEmail());
    userResponse.setPhoto(user.getPhoto());
    userResponse.setRole(user.getRoles().get(0).getName());
    return userResponse;
  }

  public List<UserResponse> map(List<User> users) {
    List<UserResponse> userResponses = new ArrayList<>(users.size());
    for (User user : users) {
      userResponses.add(map(user));
    }
    return userResponses;
  }

  public User map(UserRegisterRequest userRequest, String passwordEncrypted) {
    User userRegisterRequest = new User();
    userRegisterRequest.setFirstName(userRequest.getFirstName());
    userRegisterRequest.setLastName(userRequest.getLastName());
    userRegisterRequest.setEmail(userRequest.getEmail());
    userRegisterRequest.setPassword(passwordEncrypted);
    return userRegisterRequest;
  }

}
