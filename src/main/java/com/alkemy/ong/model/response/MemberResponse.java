package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@ApiModel
public class MemberResponse {

  private Long memberId;

  @ApiModelProperty(example = "Jane",
      required = true,
      position = 0)
  private String name;

  @ApiModelProperty(example = "Facebook.url",
      required = false,
      position = 0)
  private String facebookUrl;

  @ApiModelProperty(example = "Instagram.url",
      required = false,
      position = 0)
  private String instagramUrl;

  @ApiModelProperty(example = "Linkedin.url",
      required = false,
      position = 0)
  private String linkedinUrl;

  @ApiModelProperty(example = "https://cohorte-enero-835eb560.s3.amazonaws.com/image.png",
      required = true,
      position = 0)
  private String image;

  @ApiModelProperty(example = "This is a descritpion",
      required = true,
      position = 0)
  private String description;


}
