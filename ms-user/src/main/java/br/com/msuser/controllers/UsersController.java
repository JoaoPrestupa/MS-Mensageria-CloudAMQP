package br.com.msuser.controllers;

import br.com.msuser.dtos.UsersRecordDto;
import br.com.msuser.models.Users;
import br.com.msuser.services.UsersService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @Autowired
    private UsersService service;

    @PostMapping("/users")
    public ResponseEntity<Users> create(@RequestBody @Valid UsersRecordDto users){
        var userCreated = new Users();
        BeanUtils.copyProperties(users, userCreated);
        service.create(userCreated);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

}
