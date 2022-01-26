package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.response.SlideResponse;

import java.util.List;

public interface IGetSlideDetails {

    List<SlideResponse> find();

    List<SlideResponse> findImageOrder();
}
