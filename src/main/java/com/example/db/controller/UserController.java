package com.example.db.controller;

import com.example.db.dto.UserDto;
import com.example.db.dto.UserDtoCreate;
import com.example.db.port.in.UserPort;
import com.example.db.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController implements UserPort {

    private final UserService userService;

    @GetMapping
    @Override
    public ResponseEntity<List<UserDto>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }

    @GetMapping("/user/{email}")
    @Override
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email) {
        return ResponseEntity.ok(userService.getUserByEmail(email));
    }

    @PostMapping
    @Override
    public ResponseEntity<UserDto> create(@RequestBody UserDtoCreate userDtoCreate) {
        return ResponseEntity.ok(userService.create(userDtoCreate));
    }

    @PatchMapping("/user/{id}")
    @Override
    public ResponseEntity<UserDto> patchUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userService.patch(userDto));
    }

    @PatchMapping("/user/{email}")
    @Override
    public ResponseEntity<UserDto> changeStatusUser(@PathVariable String email) {
        return ResponseEntity.ok(userService.changeStatusUser(email));
    }

    @PostMapping("/login")
    @Override
    public ResponseEntity<UserDto> login(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email");
        String password = loginRequest.get("password");
        return ResponseEntity.ok(userService.login(email, password));
    }

}