package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.ListNewsResponse;
import org.springframework.data.domain.Pageable;

public interface IGetNewsDetails {

  ListNewsResponse findAll(Pageable pageable);

}
