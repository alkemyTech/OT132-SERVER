package com.alkemy.ong.service;

import com.alkemy.ong.mapper.ActivityMapper;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.request.ActivityRequest;
import com.alkemy.ong.model.response.ActivityResponse;
import com.alkemy.ong.repository.IActivityRepository;
import com.alkemy.ong.service.abstraction.ICreateActivityService;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService implements ICreateActivityService {

  @Autowired
  private IActivityRepository activityRepository;

  @Autowired
  private ActivityMapper activityMapper;

  @Override
  public ActivityResponse create(ActivityRequest activityRequest) {
    Activity activity = new Activity();
    activity.setName(activityRequest.getName());
    activity.setContent(activityRequest.getContent());
    activity.setImage(activityRequest.getImage());
    activity.setSoftDelete(false);
    activity.setTimestamp(new Timestamp(System.currentTimeMillis()));
    return activityMapper.map(activityRepository.save(activity));
  }
}
