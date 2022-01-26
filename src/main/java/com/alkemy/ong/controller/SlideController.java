package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.service.SlideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("slides")
public class SlideController {

    @Autowired
    SlideService slideService;

    @GetMapping
    public ResponseEntity<List<SlideResponse>> getImageOrder() {
        List<SlideResponse> response = slideService.findAll();
        return ResponseEntity.ok().body(response);
    }
}
