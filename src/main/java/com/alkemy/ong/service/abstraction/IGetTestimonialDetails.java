package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.ListTestimonialResponse;
import org.springframework.data.domain.Pageable;

public interface IGetTestimonialDetails {

  ListTestimonialResponse findAll(Pageable pageable);
}
