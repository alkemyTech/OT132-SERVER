package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationResponse {

  private String name;
  private String image;
  private String address;
  private Integer phone;

  @JsonInclude(Include.NON_NULL)
  private List<SlideResponse> slides;

  @JsonInclude(Include.NON_NULL)
  private String email;

  @JsonInclude(Include.NON_NULL)
  private String welcomeText;

  @JsonInclude(Include.NON_NULL)
  private String aboutUsText;

  @JsonInclude(Include.NON_NULL)
  private String facebookUrl;

  @JsonInclude(Include.NON_NULL)
  private String linkedinUrl;

  @JsonInclude(Include.NON_NULL)
  private String instagramUrl;
}
