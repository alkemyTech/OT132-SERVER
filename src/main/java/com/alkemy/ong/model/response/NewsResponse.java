package com.alkemy.ong.model.response;

import java.sql.Timestamp;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsResponse {

  private String name;

  private String text;

  private String image;
	
	private Timestamp timestamp;
}
