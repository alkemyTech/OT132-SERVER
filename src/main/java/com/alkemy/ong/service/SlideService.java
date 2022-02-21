package com.alkemy.ong.service;

import com.alkemy.ong.common.amazon.Image;
import com.alkemy.ong.common.amazon.ImageUtils;
import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.mapper.attribute.SlideAttributes;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.request.CreateSlideRequest;
import com.alkemy.ong.model.request.UpdateSlideRequest;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.ICreateSlide;
import com.alkemy.ong.service.abstraction.IDeleteSlide;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;
import com.alkemy.ong.service.abstraction.IUpdateSlide;
import java.text.MessageFormat;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlideService implements IGetSlideDetails, ICreateSlide, IDeleteSlide, IUpdateSlide {

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

  @Override
  public SlideResponse getBy(Long id) {
    return slideMapper.map(find(id), SlideAttributes.SLIDE_ID, SlideAttributes.IMAGE_URL,
        SlideAttributes.TEXT, SlideAttributes.ORDER);
  }

  @Override
  public SlideResponse create(CreateSlideRequest request, String fileName, String contentType)
      throws ExternalServiceException {
    Image image = new Image(request.getFileEncodeBase64(), fileName, contentType);
    Slide slide = slideRepository.save(buildSlide(imageUtils.upload(image),
        fileName,
        getOrderOrDefault(request.getOrder())));
    return slideMapper.map(
        slide,
        SlideAttributes.IMAGE_URL,
        SlideAttributes.ORDER,
        SlideAttributes.TEXT);
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

  private Slide buildSlide(String imageUrl, String text, Integer order) {
    Slide slide = new Slide();
    slide.setImageUrl(imageUrl);
    slide.setText(text);
    slide.setOrder(order);
    return slide;
  }

  private Integer getOrderOrDefault(Integer order) {
    return (order == null) ? slideRepository.getMaxOrder() + 1 : order;
  }

  @Override
  public void delete(Long id) {
    if (!slideRepository.existsById(id)) {
      throw new NotFoundException(MessageFormat.format("Slide ID: {0} not found.", id));
    }
    slideRepository.deleteById(id);
  }

  @Override
  public SlideResponse update(UpdateSlideRequest updateSlideRequest, Long id) {
    Slide slide = find(id);
    slide.setImageUrl(updateSlideRequest.getImageUrl());
    slide.setOrder(updateSlideRequest.getOrder());
    slide.setText(updateSlideRequest.getText());
    return slideMapper.map(slideRepository.save(slide), SlideAttributes.IMAGE_URL,
        SlideAttributes.ORDER,
        SlideAttributes.TEXT);
  }

  private Slide find(Long id) {
    Optional<Slide> result = slideRepository.findById(id);
    if (result.isEmpty()) {
      throw new NotFoundException("Slide could not be found.");
    }
    return result.get();
  }
}

