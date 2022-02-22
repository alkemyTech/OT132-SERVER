package com.alkemy.ong.integration.activity;

import static org.mockito.Mockito.when;

import com.alkemy.ong.integration.common.AbstractBaseIntegrationTest;
import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.repository.IActivityRepository;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.springframework.boot.test.mock.mockito.MockBean;

public abstract class AbstractBaseActivityIntegrationTest extends AbstractBaseIntegrationTest {

  protected final static long ACTIVITY_ID = 1L;
  protected static final String NAME = "Something something";
  protected static final String CONTENT = "Something Else";
  protected static final String IMAGE = "https://that.png";
  protected static final String PATH = "/activities";
  protected final static String PATH_ID = PATH + "/" + ACTIVITY_ID;
  protected final static boolean SOFT_DELETE = false;
  protected static final String NAME_OBJ = "Name";
  protected static final String CONTENT_OBJ = "Content";
  protected static final String IMAGE_OBJ = "Image";


  @MockBean
  protected IActivityRepository activityRepository;

  @Before
  public void checkFindMethod() {
    when(activityRepository.findAll()).thenReturn(buildActivityStub());
  }

  protected List<Activity> buildActivityStub() {
    List<Activity> activities = new ArrayList<>();
    activities.add(createActivityStub());
    return activities;
  }

  protected Activity createActivityStub() {
    Activity activity = new Activity();
    activity.setName(NAME);
    activity.setContent(CONTENT);
    activity.setImage(IMAGE);
    activity.setSoftDelete(SOFT_DELETE);
    activity.setTimestamp(Timestamp.from(Instant.now()));
    return activity;
  }

}
