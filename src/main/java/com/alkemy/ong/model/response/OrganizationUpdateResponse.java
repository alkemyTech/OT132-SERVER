package com.alkemy.ong.model.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationUpdateResponse {

  private String name;

  private String image;

  private String email;

  private String welcomeText;

  private String address;

  private Integer phone;

  private String aboutUsText;

  private String facebookUrl;

  private String linkedinUrl;

  private String instagramUrl;
}
