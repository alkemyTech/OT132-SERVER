package com.alkemy.ong.service.abstraction;

import com.alkemy.ong.model.request.UpdateActivityRequest;
import com.alkemy.ong.model.response.ActivityResponse;

public interface IUpdateActivity {

  ActivityResponse update(long id, UpdateActivityRequest updateActivityRequest);

}
