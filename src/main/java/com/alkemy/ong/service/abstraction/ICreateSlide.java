package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.model.request.CreateSlideRequest;
import com.alkemy.ong.model.response.SlideResponse;

public interface ICreateSlide {

  SlideResponse create(CreateSlideRequest request, String fileName, String contentType)
      throws ExternalServiceException;
}
