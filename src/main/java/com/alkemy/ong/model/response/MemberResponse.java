package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MemberResponse {

  private Long memberId;

  private String name;

  private String facebookUrl;

  private String instagramUrl;

  private String linkedinUrl;

  private String image;

  private String description;


}
