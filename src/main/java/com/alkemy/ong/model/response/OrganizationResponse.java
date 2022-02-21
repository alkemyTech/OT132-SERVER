package com.alkemy.ong.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class OrganizationResponse {

  @ApiModelProperty(example = "Somos mas",
      position = 0)
  private String name;
  @ApiModelProperty(example = "https://cohorte-enero-835eb560.s3.amazonaws.com/image.png",
      position = 1)
  private String image;
  @ApiModelProperty(example = "Address 123",
      position = 2)
  private String address;
  @ApiModelProperty(example = "3123124124",
      dataType = "integer",
      position = 3)
  private Integer phone;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(example = "{\n  Slide\n}",
      dataType = "list<slideResponse>",
      position = 4)
  private List<SlideResponse> slides;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(example = "somosmas@email.com",
      position = 5)
  private String email;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(example = "Welcome!!!",
      position = 6)
  private String welcomeText;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(example = "About us",
      position = 7)
  private String aboutUsText;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(example = "facebook.com",
      position = 8)
  private String facebookUrl;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(example = "linkedin.com",
      position = 9)
  private String linkedinUrl;

  @JsonInclude(Include.NON_NULL)
  @ApiModelProperty(example = "instagram.com",
      position = 10)
  private String instagramUrl;
}
