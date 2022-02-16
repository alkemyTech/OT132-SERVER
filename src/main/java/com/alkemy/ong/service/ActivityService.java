package com.alkemy.ong.service;

import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.mapper.ActivityMapper;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.request.CreateActivityRequest;
import com.alkemy.ong.model.request.UpdateActivityRequest;
import com.alkemy.ong.model.response.ActivityResponse;
import com.alkemy.ong.repository.IActivityRepository;
import com.alkemy.ong.service.abstraction.ICreateActivity;
import com.alkemy.ong.service.abstraction.IUpdateActivity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService implements ICreateActivity, IUpdateActivity {

  @Autowired
  private IActivityRepository activityRepository;

  @Autowired
  private ActivityMapper activityMapper;

  @Override
  public ActivityResponse create(CreateActivityRequest createActivityRequest) {
    Activity activity = new Activity();
    activity.setName(createActivityRequest.getName());
    activity.setContent(createActivityRequest.getContent());
    activity.setImage(createActivityRequest.getImage());
    activity.setSoftDelete(false);
    return activityMapper.map(activityRepository.save(activity));
  }

  @Override
  public ActivityResponse update(long id, UpdateActivityRequest updateActivityRequest) {
    Activity activity = findBy(id);
    if (activity == null) {
      throw new NotFoundException("Activity not found");
    }
    activity.setName(updateActivityRequest.getName());
    activity.setContent(updateActivityRequest.getContent());
    activity.setImage(updateActivityRequest.getImage());
    return activityMapper.map(activityRepository.save(activity));
  }

  private Activity findBy(long id) {
    return activityRepository.findByActivityIdAndSoftDeleteFalse(id);
  }
}
