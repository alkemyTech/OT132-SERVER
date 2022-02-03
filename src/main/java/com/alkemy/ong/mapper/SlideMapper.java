package com.alkemy.ong.mapper;

import com.alkemy.ong.mapper.attribute.SlideAttributes;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.SlideResponse;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

  public SlideResponse map(Slide slide, SlideAttributes... slideAttributes) {
    SlideResponse slideResponse = new SlideResponse();
    for (SlideAttributes slideAttribute : slideAttributes) {
      switch (slideAttribute) {
        case TEXT:
          slideResponse.setText(slide.getText());
          break;
        case ORDER:
          slideResponse.setOrder(slide.getOrder());
          break;
        case IMAGE_URL:
          slideResponse.setImageUrl(slide.getImageUrl());
          break;
        default:
          throw new UnsupportedOperationException(
              MessageFormat.format("Slide attribute: {0} is unsupported", slideAttribute));
      }
    }
    return slideResponse;
  }

  public List<SlideResponse> map(List<Slide> slides, SlideAttributes... slideAttributes) {
    List<SlideResponse> slideResponses = new ArrayList<>(slides.size());
    for (Slide slide : slides) {
      slideResponses.add(map(slide, slideAttributes));
    }
    return slideResponses;
  }
}
