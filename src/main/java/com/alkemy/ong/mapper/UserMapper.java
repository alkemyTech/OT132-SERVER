package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.User;
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
    userResponse.setRoles(user.getRoles());
    return userResponse;
  }

  public List<UserResponse> mapToList(List<User> users) {
    List<UserResponse> userResponses = new ArrayList<>();
    for (User user : users) {
      userResponses.add(map(user));
    }
    return userResponses;
  }
}
