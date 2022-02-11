package com.alkemy.ong.controller;

import com.alkemy.ong.common.PaginatedResultsRetrieved;
import com.alkemy.ong.model.request.CreateTestimonialRequest;
import com.alkemy.ong.model.request.UpdateTestimonialRequest;
import com.alkemy.ong.model.response.ListTestimonialResponse;
import com.alkemy.ong.model.response.TestimonialResponse;
import com.alkemy.ong.service.abstraction.ICreateTestimonial;
import com.alkemy.ong.service.abstraction.IDeleteTestimonial;
import com.alkemy.ong.service.abstraction.IGetTestimonialDetails;
import com.alkemy.ong.service.abstraction.IUpdateTestimonial;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/testimonials")
public class TestimonialController {

  private static final String TESTIMONIAL_PATH = "/testimonials";

  @Autowired
  private IGetTestimonialDetails getTestimonialDetails;

  @Autowired
  private ICreateTestimonial createTestimonial;

  @Autowired
  private IDeleteTestimonial deleteTestimonial;

  @Autowired
  private IUpdateTestimonial updateTestimonial;

  @Autowired
  private PaginatedResultsRetrieved resultsRetrieved;

  @GetMapping
  public ResponseEntity<ListTestimonialResponse> list(Pageable pageable,
      UriComponentsBuilder uriBuilder, HttpServletResponse response) {
    ListTestimonialResponse listTestimonialResponse = getTestimonialDetails.findAll(pageable);

    resultsRetrieved.addLinkHeaderOnPagedResourceRetrieval(uriBuilder,
        response,
        TESTIMONIAL_PATH,
        listTestimonialResponse.getPage(),
        listTestimonialResponse.getTotalPages(),
        listTestimonialResponse.getSize());
    
    return ResponseEntity.ok().body(listTestimonialResponse);
  }

  @PostMapping
  public ResponseEntity<TestimonialResponse> create(
      @RequestBody @Valid CreateTestimonialRequest createTestimonialRequest) {
    TestimonialResponse testimonialResponse = createTestimonial.create(createTestimonialRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(testimonialResponse);
  }

  @PutMapping("{id}")
  public ResponseEntity<TestimonialResponse> update(@PathVariable Long id,
      @RequestBody @Valid UpdateTestimonialRequest updateTestimonialRequest) {
    TestimonialResponse testimonialResponse = updateTestimonial.update(id,
        updateTestimonialRequest);
    return ResponseEntity.status(HttpStatus.OK).body(testimonialResponse);
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteTestimonial.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
