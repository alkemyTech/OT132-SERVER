package com.alkemy.ong.controller;

import com.alkemy.ong.model.response.SlideResponse;
import com.alkemy.ong.service.abstraction.IGetSlideDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.jaas.AuthorityGranter;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("slides")
public class SlideController {

    @Autowired
    private IGetSlideDetails iGetSlideDetails;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<List<SlideResponse>> getImageOrder() {
        List<SlideResponse> response = iGetSlideDetails.findImageOrder();
        return ResponseEntity.ok().body(response);
    }
}
