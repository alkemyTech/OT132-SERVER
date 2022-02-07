package com.alkemy.ong.model.request;

import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class CreateSlideRequest {

  @NotBlank(message = "File encoded in base64 must be provided.")
  private String fileEncodeBase64;

  @Nullable
  private Integer order;
}
