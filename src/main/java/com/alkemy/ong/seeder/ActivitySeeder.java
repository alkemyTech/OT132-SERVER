package com.alkemy.ong.seeder;

import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.repository.IActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ActivitySeeder implements CommandLineRunner {

    @Autowired
    IActivityRepository iActivityRepository;

    @Override
    public void run(String... args) throws Exception {
        seedActivityTable();

    }

    private void seedActivityTable() {
        if (iActivityRepository.count() == 0) {
            Activity act1 = new Activity();
            act1.setName("School support for the primary level");
            act1.setContent(
                    "The workshops are held from Monday to Thursday from 10 a.m. to 12 p.m. and from 2 p.m. to 4 p.m. in the counter shift.");
            act1.setImage("-");
            iActivityRepository.save(act1);
            Activity act2 = new Activity();
            act2.setName("Secondary school support");
            act2.setContent(
                    "The workshops are held from Monday to Friday from 10 a.m. to 12 p.m. and from 4 p.m. to 6 p.m. in the counter shift.");
            act2.setImage("-");
            iActivityRepository.save(act2);
            Activity act3 = new Activity();
            act3.setName("Tutorials");
            act3.setContent(
                    "Its objective is to guarantee permanence in school and build a life project that gives meaning to school.");
            act3.setImage("-");
            iActivityRepository.save(act3);
        }
    }

}
