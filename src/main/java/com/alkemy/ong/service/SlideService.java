package com.alkemy.ong.service;

import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlideService implements IGetSlideDetails {

    @Autowired
    private SlideMapper mapper;
    @Autowired
    private ISlideRepository slideRepository;


    @Override
    public List<SlideResponse> findAll() {
        List<Slide> slides = slideRepository.findAll();
        return null;
    }
}
