package com.alkemy.ong.seeder;

import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.repository.IActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ActivitySeeder implements CommandLineRunner {

  @Autowired
  private IActivityRepository activityRepository;

  @Override
  public void run(String... args) {
    seedActivityTable();
  }

  private void seedActivityTable() {
    if (activityRepository.count() == 0) {
      createActivity("School support for the primary level",
          "The workshops are held from Monday to Thursday from 10 a.m. to 12 p.m. and from 2 p.m. to 4 p.m. in the counter shift."
      );
      createActivity("Secondary school support",
          "The workshops are held from Monday to Friday from 10 a.m. to 12 p.m. and from 4 p.m. to 6 p.m. in the counter shift."
      );
      createActivity("Tutorials",
          "Its objective is to guarantee permanence in school and build a life project that gives meaning to school."
      );
    }
  }

  private void createActivity(String name, String content) {
    Activity activity = new Activity();
    activity.setName(name);
    activity.setContent(content);
    activity.setImage("-");
    activityRepository.save(activity);
  }

}
