package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.UpdateSlideRequest;
import com.alkemy.ong.model.response.SlideResponse;

public interface IUpdateSlide {

  SlideResponse update(UpdateSlideRequest updateSlideRequest, Long id);
}
