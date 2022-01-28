package com.alkemy.ong.mapper;

import com.alkemy.ong.mapper.enums.SlideOperation;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.SlideResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

  public SlideResponse map(Slide slide, String operation) {
    SlideResponse slideResponse = new SlideResponse();
    if (operation.equals(SlideOperation.ALL.name())) {
      slideResponse.setImageUrl(slide.getImageUrl());
      slideResponse.setText(slide.getText());
      slideResponse.setOrder(slide.getOrder());
      return slideResponse;
    } else if (operation.equals(SlideOperation.IMAGEORDER.name())) {
      slideResponse.setImageUrl(slide.getImageUrl());
      slideResponse.setOrder(slide.getOrder());
      return slideResponse;
    } else {
      return null;
    }
  }

  public List<SlideResponse> mapList(List<Slide> slides, String operation) {
    List<SlideResponse> slideResponses = new ArrayList<SlideResponse>(slides.size());
    for (Slide slide : slides) {
      slideResponses.add(this.map(slide, operation));
    }
    return slideResponses;
  }

}
