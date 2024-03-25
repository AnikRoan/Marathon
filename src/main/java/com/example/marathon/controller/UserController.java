package com.example.marathon.controller;

import com.example.marathon.entity.User;
import com.example.marathon.service.CustomUserDeteilService;
import jakarta.transaction.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class UserController {

    private final CustomUserDeteilService userDeteilService;

    public UserController(CustomUserDeteilService userDeteilService) {
        this.userDeteilService = userDeteilService;
    }

    @PostMapping("/save")
    public void save(@RequestBody User user){
        userDeteilService.createUser(user);

    }
    //read,write,delete,access,execute ===> authorities
    //ADMIN,MANAGER,USER,CLIENT,ADMIN ===> roles
    //GrantedAuthority ===> something with a name
    //ROLE_ADMIN,ROLE_USER,ROLE_MANAGER,ROLE_CLIENT ===>
}
