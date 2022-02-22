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

  @ApiModelProperty(example = "ID",
      dataType = "long",
      position = 0)
  private Long memberId;

  @ApiModelProperty(example = "Jane",
      position = 1)
  private String name;

  @ApiModelProperty(example = "Facebook.url",
      position = 2)
  private String facebookUrl;

  @ApiModelProperty(example = "Instagram.url",
      position = 3)
  private String instagramUrl;

  @ApiModelProperty(example = "Linkedin.url",
      position = 4)
  private String linkedinUrl;

  @ApiModelProperty(example = "https://cohorte-enero-835eb560.s3.amazonaws.com/image.png",
      position = 5)
  private String image;

  @ApiModelProperty(example = "This is a descritpion",
      position = 6)
  private String description;
}
