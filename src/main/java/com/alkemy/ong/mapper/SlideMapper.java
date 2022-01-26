package com.alkemy.ong.mapper;

import java.util.ArrayList;
import java.util.List;

import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.SlideResponse;

import org.springframework.stereotype.Component;

@Component
public class SlideMapper {
    
    public SlideResponse map(Slide slide){
        SlideResponse slideResponse = new SlideResponse();
        slideResponse.setImageUrl(slide.getImageUrl());
        slideResponse.setText(slide.getText());
        slideResponse.setOrder(slide.getOrder());
        return slideResponse;
    }

    public List<SlideResponse> mapList(List<Slide> slides){
        List<SlideResponse> listSlides = new ArrayList<SlideResponse>();
        for(Slide slide : slides){
            listSlides.add(this.map(slide));
        }
        return listSlides;
    }
}
