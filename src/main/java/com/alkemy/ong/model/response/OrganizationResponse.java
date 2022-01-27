
package com.alkemy.ong.model.response;

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
  private List<SlideResponse> slides;

}
