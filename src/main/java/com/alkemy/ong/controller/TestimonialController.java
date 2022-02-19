package com.alkemy.ong.controller;

import com.alkemy.ong.common.PaginatedResultsRetrieved;
import com.alkemy.ong.exception.ErrorResponse;
import com.alkemy.ong.model.request.CreateTestimonialRequest;
import com.alkemy.ong.model.request.UpdateTestimonialRequest;
import com.alkemy.ong.model.response.ListTestimonialResponse;
import com.alkemy.ong.model.response.TestimonialResponse;
import com.alkemy.ong.service.abstraction.ICreateTestimonial;
import com.alkemy.ong.service.abstraction.IDeleteTestimonial;
import com.alkemy.ong.service.abstraction.IGetTestimonialDetails;
import com.alkemy.ong.service.abstraction.IUpdateTestimonial;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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
@Api(tags = "Testimonial Endpoints", value = "TestimonialEndpoints")
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

  @GetMapping(produces = {"application/json"})
  @ApiOperation(value = "Get pageable of testimonial", produces = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - List of testimonial",
          response = ListTestimonialResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class)})
  @ApiImplicitParam(name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
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

  @PostMapping(produces = {"application/json"},
      consumes = {"application/json"})
  @ApiOperation(value = "Create a testimonial", produces = "application/json",
      consumes = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - Testimonial created",
          response = TestimonialResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Bad request",
          response = ErrorResponse.class)})
  @ApiImplicitParam(name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  public ResponseEntity<TestimonialResponse> create(
      @RequestBody @Valid CreateTestimonialRequest createTestimonialRequest) {
    TestimonialResponse testimonialResponse = createTestimonial.create(createTestimonialRequest);
    return ResponseEntity.status(HttpStatus.CREATED).body(testimonialResponse);
  }

  @PutMapping(value = "{id}", produces = {"application/json"},
      consumes = {"application/json"})
  @ApiOperation(value = "Update a testimonial", produces = "application/json",
      consumes = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - Updated Testimonial",
          response = TestimonialResponse.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 400, message = "INVALID_ARGUMENT - Bad request",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - Testimonial not found",
          response = ErrorResponse.class)})
  @ApiImplicitParam(name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  public ResponseEntity<TestimonialResponse> update(@PathVariable Long id,
      @RequestBody @Valid UpdateTestimonialRequest updateTestimonialRequest) {
    TestimonialResponse testimonialResponse = updateTestimonial.update(id,
        updateTestimonialRequest);
    return ResponseEntity.status(HttpStatus.OK).body(testimonialResponse);
  }

  @DeleteMapping(value = "{id}", produces = {"application/json"})
  @ApiOperation(value = "Delete a testimonial", produces = "application/json")
  @ApiResponses(value = {
      @ApiResponse(code = 200, message = "OK - Testimonial deleted",
          response = Void.class),
      @ApiResponse(code = 403, message = "PERMISSION_DENIED - Forbidden.",
          response = ErrorResponse.class),
      @ApiResponse(code = 404, message = "NOT_FOUND - Testimonial not found",
          response = ErrorResponse.class)})
  @ApiImplicitParam(name = "Authorization",
      value = "Access Token",
      required = true,
      allowEmptyValue = false,
      paramType = "header",
      dataTypeClass = String.class,
      example = "Bearer access_token")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    deleteTestimonial.delete(id);
    return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
  }
}
