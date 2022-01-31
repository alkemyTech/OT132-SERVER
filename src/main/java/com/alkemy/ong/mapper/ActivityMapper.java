package com.alkemy.ong.mapper;

import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.response.ActivityResponse;

public class ActivityMapper {

  public ActivityResponse map (Activity activity){
    ActivityResponse activityResponse = new ActivityResponse();
    activityResponse.setName(activity.getName());
    activityResponse.setContent(activity.getContent());
    activityResponse.setImage(activity.getImage());
    return activityResponse;
  }
}
