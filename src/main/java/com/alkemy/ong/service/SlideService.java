package com.alkemy.ong.service;

import com.alkemy.ong.common.amazon.Image;
import com.alkemy.ong.common.amazon.ImageUtils;
import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.SlideMapper;
import com.alkemy.ong.mapper.attribute.SlideAttributes;
import com.alkemy.ong.model.entity.Slide;
import com.alkemy.ong.model.entity.User;
import com.alkemy.ong.model.request.CreateSlideRequest;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.repository.ISlideRepository;
import com.alkemy.ong.service.abstraction.ICreateSlide;
import com.alkemy.ong.service.abstraction.IDeleteSlide;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SlideService implements IGetSlideDetails, ICreateSlide, IDeleteSlide {

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
    Slide slide = slideRepository.deleteSlidesBySlideId(id);
    if (slide == null){
      throw new NotFoundException("Slide ID: "+id.toString()+" does not exist.");
    }



    user.setSoftDelete(true);
    userRepository.save(user);
  }

}
