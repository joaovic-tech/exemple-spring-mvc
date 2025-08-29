package tech.joaovic.exemple_spring_mvc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import tech.joaovic.exemple_spring_mvc.models.UserModel;
import tech.joaovic.exemple_spring_mvc.services.IUserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("users")
public class UserController {
    @Autowired
    IUserService iUserService;

    @PostMapping
    public ResponseEntity<Object> createUser(@Valid @RequestBody UserModel userModel) {
        UserModel user = iUserService.createUser(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping()
    public ResponseEntity<List<UserModel>> findAll() {
        List<UserModel> users = iUserService.findAll();
        return ResponseEntity.ok(users);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserModel> updateUser(@PathVariable UUID id, @Valid @RequestBody UserModel userModel) {
        UserModel user = iUserService.updateUser(id, userModel);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserModel> deleteUser(@PathVariable UUID id) {
        UserModel user = iUserService.deleteUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

}
