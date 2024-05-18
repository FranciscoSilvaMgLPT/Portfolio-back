package com.example.db.port.in;

import com.example.db.dto.UserDto;
import com.example.db.dto.UserDtoCreate;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Map;

public interface UserPort {
    ResponseEntity<List<UserDto>> getUsers();
    ResponseEntity<UserDto> getUserByEmail(String email);
    ResponseEntity<UserDto> create(UserDtoCreate userDtoCreate);
    ResponseEntity<UserDto> patchUser(UserDto userDto);
    ResponseEntity<UserDto> changeStatusUser(String email);
    ResponseEntity<UserDto> login(Map<String, String> loginRequest);
}
