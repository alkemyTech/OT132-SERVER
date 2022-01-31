package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.model.request.ActivityRequest;
import com.alkemy.ong.model.response.ActivityResponse;

public interface ICreateActivityService {

ActivityResponse create(ActivityRequest activityRequest);
}
