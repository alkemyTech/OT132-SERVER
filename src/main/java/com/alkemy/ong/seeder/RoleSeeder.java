package com.alkemy.ong.seeder;

import com.alkemy.ong.config.segurity.RoleType;
import com.alkemy.ong.model.entity.Role;
import com.alkemy.ong.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class RoleSeeder implements CommandLineRunner {

  @Autowired
  private IRoleRepository roleRepository;

  @Override
  @Order(1)
  public void run(String... args) {
    seedRoleTable();
  }

  private void seedRoleTable() {
    if (roleRepository.count() == 0) {
      createRole(RoleType.ADMIN);
      createRole(RoleType.USER);
    }
  }

  private void createRole(RoleType roleType) {
    Role role = new Role();
    role.setName(roleType.getFullRoleName());
    role.setDescription(roleType.name());
    roleRepository.save(role);
  }

}
