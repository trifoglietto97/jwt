package com.example.jwt;

import com.example.jwt.entity.ERole;
import com.example.jwt.entity.Role;
import com.example.jwt.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class JwtApplication {

    @Autowired
    private RoleRepository roleRepository;

    public static void main(String[] args) {
        var context = SpringApplication.run(JwtApplication.class, args);

    }

/*
    @Bean
     void addRoles(){
        Role role1 = new Role();
        role1.setName(ERole.ROLE_ADMIN);

        Role role2 = new Role();
        role2.setName(ERole.ROLE_USER);

        roleRepository.save(role1);
        roleRepository.save(role2);
    }

 */

}
