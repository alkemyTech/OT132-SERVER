package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("slides")
public class SlideController {

  @Autowired
  private IGetSlideDetails slideDetails;

  @GetMapping
  @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
  public ResponseEntity<Map<String, List<SlideResponse>>> list() {
    List<SlideResponse> response = slideDetails.findImageOrder();
    Map<String, List<SlideResponse>> map = new HashMap<>();
    map.put("Sliders", response);
    return ResponseEntity.ok().body(map);
  }
}
