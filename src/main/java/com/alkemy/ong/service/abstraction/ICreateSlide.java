package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.model.response.SlideResponse;

public interface ICreateSlide {

  SlideResponse create(String file, String fileName, String contentType, Integer order)
      throws ExternalServiceException;
}
