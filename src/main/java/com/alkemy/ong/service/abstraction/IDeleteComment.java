package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.InsufficientPermissionsException;
import org.springframework.security.core.Authentication;

public interface IDeleteComment {

  void delete(Long id, Authentication authentication) throws InsufficientPermissionsException;
  
}
