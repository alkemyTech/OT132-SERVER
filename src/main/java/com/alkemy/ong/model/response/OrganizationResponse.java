package com.alkemy.ong.model.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationResponse {

  private String name;
  private String image;
  private String address;
  private Integer phone;
  private List<SlideResponse> slides;

  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String facebookUrl;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String linkedinUrl;
  @JsonInclude(JsonInclude.Include.NON_NULL)
  private String instagramUrl;
}
