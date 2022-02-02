package com.alkemy.ong.service;

import com.alkemy.ong.common.amazon.Image;
import com.alkemy.ong.common.amazon.ImageUtils;
import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.mapper.attribute.SlideAttributes;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.request.CreateSlideRequest;
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
  private ImageUtils imageUtils;

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
  public SlideResponse create(CreateSlideRequest request, String fileName, String contentType)
      throws ExternalServiceException {
    Image image = new Image(request.getFileEncodeBase64(), fileName, contentType);
    return slideMapper.map(
        slideRepository.save(buildSlideResponse(imageUtils.upload(image),
            fileName,
            getOrderOrDefault(request.getOrder()))),
        SlideAttributes.IMAGE_URL,
        SlideAttributes.ORDER,
        SlideAttributes.TEXT);

  }

  private Slide buildSlideResponse(String imageUrl, String text, Integer order) {
    Slide slide = new Slide();
    slide.setImageUrl(imageUrl);
    slide.setText(text);
    slide.setOrder(order);
    return slide;
  }

  private Integer getOrderOrDefault(Integer order) {
    if (order == null) {
      // List<Slide> slides = slideRepository.findOne(Sort.by(direction, properties))
      // order = slides.get(slides.size() - 1).getOrder() + 1;
      order = slideRepository.getMaxOrder() + 1;
    }
    return order;
  }

}
