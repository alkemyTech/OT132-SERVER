package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.SlideResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.List;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.SlideResponse;

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
}
