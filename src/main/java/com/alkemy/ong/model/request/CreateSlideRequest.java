package com.alkemy.ong.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@ApiModel
public class CreateSlideRequest {

  @NotBlank(message = "File encoded in base64 must be provided.")
  @ApiModelProperty(example = "iVBORw0KGgoAAAANSUhEUgAAAoAAAAGpCAYAAAAdhodAAA...",
      required = true,
      position = 0)
  private String fileEncodeBase64;

  @Nullable
  @ApiModelProperty(example = "1",
      required = true,
      dataType = "integer",
      position = 1)
  private Integer order;
}
