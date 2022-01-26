package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.SlideResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("Slides")
public class SlideController {

@GetMapping
    public ResponseEntity<SlideResponse> getImageOrder(){

}
}
