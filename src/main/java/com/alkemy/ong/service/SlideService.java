package com.alkemy.ong.service;

import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.mapper.enums.SlideOperation;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;
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
    List<SlideResponse> slides = slideMapper.mapList(slideRepository.findAll(),
        SlideOperation.IMAGEORDER.name());
    ListSlideResponse listSlideResponse = new ListSlideResponse();
    listSlideResponse.setSlideResponses(slides);
    return listSlideResponse;
  }

}
