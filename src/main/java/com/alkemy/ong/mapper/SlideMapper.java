package com.alkemy.ong.mapper;

import com.alkemy.ong.mapper.enums.SlideOperation;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.SlideResponse;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

  public SlideResponse map(Slide slide, SlideOperation slideOperation) {
    SlideResponse slideResponse = new SlideResponse();
    switch (slideOperation) {
      case LIST_IN_ORGANIZATIONS:
        slideResponse.setText(slide.getText());
        slideResponse.setOrder(slide.getOrder());
        slideResponse.setImageUrl(slide.getImageUrl());
        break;
      case LIST:
        slideResponse.setOrder(slide.getOrder());
        slideResponse.setImageUrl(slide.getImageUrl());
        break;
      default:
        throw new UnsupportedOperationException(
            MessageFormat.format("Slide operation: {0} is unsupported", slideOperation));
    }
    return slideResponse;
  }

  public List<SlideResponse> mapList(List<Slide> slides, SlideOperation slideOperation) {
    List<SlideResponse> slideResponses = new ArrayList<SlideResponse>(slides.size());
    for (Slide slide : slides) {
      slideResponses.add(this.map(slide, slideOperation));
    }
    return slideResponses;
  }

}
