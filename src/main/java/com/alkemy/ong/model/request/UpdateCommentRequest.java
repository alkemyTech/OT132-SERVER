package com.alkemy.ong.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ApiModel
public class UpdateCommentRequest {

  @NotNull(message = "The user ID cannot be null or empty.")
  @ApiModelProperty(example = "2",
      required = true,
      position = 0)
  private Long userId;

  @NotNull(message = "The news ID cannot be null or empty.")
  @ApiModelProperty(example = "3",
      required = true,
      position = 1)
  private Long newsId;

  @NotBlank(message = "The body cannot be null or empty.")
  @ApiModelProperty(example = "This is a comment",
      required = true,
      position = 2)
  private String body;
}
