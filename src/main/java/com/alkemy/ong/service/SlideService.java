package com.alkemy.ong.service;

import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SlideService implements IGetSlideDetails {

  @Autowired
  private SlideMapper mapper;
  @Autowired
  private ISlideRepository slideRepository;


  @Override
  public List<SlideResponse> find() {
    List<Slide> slides = slideRepository.findAll();
    return mapper.mapList(slides);
  }

  @Override
  public List<SlideResponse> findImageOrder() {
    List<Slide> slides = slideRepository.findAll();
    return mapper.mapAllImageOrder(slides);
  }
}
