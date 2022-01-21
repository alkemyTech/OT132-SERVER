package com.alkemy.ong.seeder;

import com.alkemy.ong.model.entity.Activity;
import com.alkemy.ong.repository.IActivityRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class ActivitySeeder implements CommandLineRunner {

    @Autowired 
    IActivityRepository iActivityRepository;

    @Override
    public void run(String... args) throws Exception {
        // TODO Auto-generated method stub
        
    }
    
    private void seedActivityTable(){

    }

}
