package com.alkemy.ong.controller;

import com.alkemy.ong.exception.ExternalServiceException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.request.CreateSlideRequest;
import com.alkemy.ong.model.response.ListSlideResponse;
import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.service.abstraction.ICreateSlide;
import com.alkemy.ong.service.abstraction.IDeleteSlide;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/slides")
public class SlideController {

  @Autowired
  private IGetSlideDetails getSlideDetails;

  @Autowired
  private ICreateSlide createSlide;

  @Autowired
  private IDeleteSlide deleteSlide;

  @GetMapping
  public ResponseEntity<ListSlideResponse> list() {
    return ResponseEntity.ok().body(getSlideDetails.list());
  }

  @PostMapping
  public ResponseEntity<SlideResponse> create(
      @RequestBody @Valid CreateSlideRequest request,
      @RequestHeader("x-file-name") String fileName,
      @RequestHeader("x-content-type") String contentType) throws ExternalServiceException {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(createSlide.create(request, fileName, contentType));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) throws NotFoundException {
    deleteSlide.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }

  @GetMapping("/{id}")
  public ResponseEntity<SlideResponse> getBy(@PathVariable(value = "id") Long id) {
    SlideResponse response = getSlideDetails.getBy(id);
    return ResponseEntity.ok().body(response);
  }
}
