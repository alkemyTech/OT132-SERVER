
package com.alkemy.ong.model.response;

import java.util.List;

import com.alkemy.ong.model.entity.Slide;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganizationResponse {

  private String name;
  private String image;
  private String address;
  private Integer phone;
  List<Slide> slides;

}
