package com.alkemy.ong.service;

import com.alkemy.ong.common.amazon.Image;
import com.alkemy.ong.common.amazon.ImageUtils;
import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.mapper.attribute.SlideAttributes;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.ICreateSlide;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class SlideService implements IGetSlideDetails, ICreateSlide {

  @Autowired
  private SlideMapper slideMapper;

  @Autowired
  private ISlideRepository slideRepository;

  @Autowired
  private ImageUtils utils;

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

  @Override
  public SlideResponse create(String file, String fileName, String contentType, Integer order)
      throws ExternalServiceException {
    Image image = new Image(file, fileName, contentType);
    SlideResponse slide = new SlideResponse();
    try {
      slide.setImageUrl(utils.upload(image));
      slide.setText(fileName);
      slide.setOrder(getOrder(order));
      return slide;
    } catch (ExternalServiceException e) {
      throw new ExternalServiceException(e.getMessage());
    }

  }

  private Integer getOrder(Integer order) {
    if (order == null) {
      List<Slide> slides = slideRepository.findAll(Sort.by(SlideAttributes.ORDER.getFieldName()));
      order = slides.get(slides.size() - 1).getOrder();
    }
    return order;
  }

}
