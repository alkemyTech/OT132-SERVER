package com.alkemy.ong.service;

import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.mapper.enums.SlideOperation;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SlideService implements IGetSlideDetails {

  @Autowired
  private SlideMapper slideMapper;
  @Autowired
  private ISlideRepository slideRepository;

  @Override
  public ListSlideResponse list() {
    List<Slide> slide = slideRepository.findAll();
    List<SlideResponse> slideResponses = new ArrayList<>();
    for (int i = 0; i < slide.size(); i++) {
      slideResponses.add(slideMapper.map(slide.get(i), SlideOperation.ORDER));
      slideResponses.add(slideMapper.map(slide.get(i), SlideOperation.IMAGE_URL));
    }
    ListSlideResponse listSlideResponse = new ListSlideResponse();
    listSlideResponse.setSlideResponses(slideResponses);
    return listSlideResponse;
  }

}