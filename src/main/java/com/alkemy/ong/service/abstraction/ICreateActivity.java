package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.CreateActivityRequest;
import com.alkemy.ong.model.response.ActivityResponse;

public interface ICreateActivity {

  ActivityResponse create(CreateActivityRequest createActivityRequest);
}
