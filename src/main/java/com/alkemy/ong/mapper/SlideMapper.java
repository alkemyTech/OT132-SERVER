package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.SlideResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SlideMapper {

    public List<SlideResponse> map(List<Slide> slides) {
        List<SlideResponse> slidesResponse = new ArrayList<>();
        for (Slide aux : slides) {
            SlideResponse slideResponse = new SlideResponse();
            slideResponse.setImageUrl(aux.getImageUrl());
            slideResponse.setOrder(aux.getOrder());
            slidesResponse.add(slideResponse);
        }
        return slidesResponse;
    }
}
