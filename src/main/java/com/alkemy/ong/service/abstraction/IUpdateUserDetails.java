package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.UpdateUserDetailsRequest;
import com.alkemy.ong.model.response.UserResponse;

public interface IUpdateUserDetails {

  UserResponse update(UpdateUserDetailsRequest updateUserDetailsRequest, Long userId);
}
