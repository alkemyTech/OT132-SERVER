package com.alkemy.ong.seeder;

import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.repository.IActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ActivitySeeder implements CommandLineRunner {

  private static final String PRIMARY_LEVEL = "School support for the primary level";
  private static final String SECONDARY_SCHOOL_SUPPORT = "Secondary school support";
  private static final String TUTORIALS = "Tutorials";
  private static final String PRIMARY_LEVEL_CONTENT = "The workshops are held from Monday to "
      + "Thursday from 10 a.m. to 12 p.m. and from 2 p.m. to 4 p.m. in the counter shift.";
  private static final String SECONDARY_SCHOOL_SUPPORT_CONTENT = "The workshops are held from "
      + "Monday to Friday from 10 a.m. to 12 p.m. and from 4 p.m. to 6 p.m. in the counter shift.";
  private static final String TUTORIALS_CONTENT = "Its objective is to guarantee permanence in "
      + "school and build a life project that gives meaning to school.";
  private static final String IMAGE = "https://foo.com/";

  @Autowired
  private IActivityRepository activityRepository;

  @Override
  public void run(String... args) {
    seedActivityTable();
  }

  private void seedActivityTable() {
    if (activityRepository.count() == 0) {
      createActivity(PRIMARY_LEVEL, PRIMARY_LEVEL_CONTENT);
      createActivity(SECONDARY_SCHOOL_SUPPORT, SECONDARY_SCHOOL_SUPPORT_CONTENT);
      createActivity(TUTORIALS, TUTORIALS_CONTENT);
    }
  }

  private void createActivity(String name, String content) {
    Activity activity = new Activity();
    activity.setName(name);
    activity.setContent(content);
    activity.setImage(IMAGE);
    activityRepository.save(activity);
  }

}
