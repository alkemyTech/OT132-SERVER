package com.alkemy.ong.service;

import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.mapper.attribute.SlideAttributes;
import com.alkemy.ong.model.entity.Slide;
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
    List<Slide> slide = slideRepository.findAll();
    return buildListSlideResponse(slide);
  }

  private ListSlideResponse buildListSlideResponse(List<Slide> slide) {
    List<SlideResponse> slideResponses = slideMapper.map(
        slide,
        SlideAttributes.IMAGE_URL,
        SlideAttributes.ORDER);

    ListSlideResponse listSlideResponse = new ListSlideResponse();
    listSlideResponse.setSlideResponses(slideResponses);
    return listSlideResponse;
  }

}
