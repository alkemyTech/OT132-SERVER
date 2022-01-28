package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.SlideResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class SlideMapper {

  public SlideResponse map(Slide slide) {
    SlideResponse slideResponse = new SlideResponse();
    slideResponse.setImageUrl(slide.getImageUrl());
    slideResponse.setText(slide.getText());
    slideResponse.setOrder(slide.getOrder());
    return slideResponse;
  }

  public List<SlideResponse> mapList(List<Slide> slides) {
    List<SlideResponse> slideResponses = new ArrayList<SlideResponse>(slides.size());
    for (Slide slide : slides) {
      slideResponses.add(this.map(slide));
    }
    return slideResponses;
  }

  public SlideResponse mapOneImageOrder(Slide slide) {
    SlideResponse slideResponse = new SlideResponse();
    slideResponse.setImageUrl(slide.getImageUrl());
    slideResponse.setOrder(slide.getOrder());
    return slideResponse;
  }

  public List<SlideResponse> mapAllImageOrder(List<Slide> slides) {
    List<SlideResponse> slideResponses = new ArrayList<SlideResponse>(slides.size());
    for (Slide slide : slides) {
      slideResponses.add(this.mapOneImageOrder(slide));
    }
    return slideResponses;
  }
}
