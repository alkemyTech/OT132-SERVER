package com.alkemy.ong.controller;

import com.alkemy.ong.model.request.CreateActivityRequest;
import com.alkemy.ong.model.response.ActivityResponse;
import com.alkemy.ong.service.abstraction.ICreateActivity;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("activities")
public class ActivityController {

  @Autowired
  private ICreateActivity createActivity;

  @PostMapping
  public ResponseEntity<ActivityResponse> create(
      @RequestBody @Valid CreateActivityRequest createActivityRequest) {
    ActivityResponse activityResponse = createActivity.create(createActivityRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(activityResponse);
  }

}
