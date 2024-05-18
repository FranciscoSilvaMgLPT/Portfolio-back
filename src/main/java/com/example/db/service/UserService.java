package com.example.db.service;

import com.example.db.dto.UserDto;
import com.example.db.dto.UserDtoCreate;
import com.example.db.entity.User;
import com.example.db.exceptions.NotFoundException;
import com.example.db.exceptions.ParameterNotFoundException;
import com.example.db.port.out.DatabasePort;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final DatabasePort databasePort;


    public List<UserDto> getUsers() {
        return databasePort.getUsers()
                .stream()
                .map(userFromRepository -> UserDto.builder()
                        .id(userFromRepository.getId())
                        .email(userFromRepository.getEmail())
                        .name(userFromRepository.getName())
                        .build()).toList();
    }


    public UserDto getUserByEmail(String email) {
        Optional<User> userOptional = databasePort.getUserByEmail(email);
        if(userOptional.isEmpty()) throw new NotFoundException();
        User userOpt = userOptional.get();
        return UserDto.builder()
                .id(userOpt.getId())
                .email(userOpt.getEmail())
                .name(userOpt.getName())
                .active(userOpt.isActive()).build();
    }

    public UserDto create(UserDtoCreate userDtoCreate) {
        if (userDtoCreate.getEmail() == null || userDtoCreate.getName() == null || userDtoCreate.getPassword() == null)
            throw new ParameterNotFoundException();

        String salt = BCrypt.gensalt();

        databasePort.createUser(User.builder()
                .email(userDtoCreate.getEmail())
                .name(userDtoCreate.getName())
                .password(BCrypt.hashpw(userDtoCreate.getPassword(),salt))
                .salt(salt)
                .build());

        return  UserDto.builder()
                .email(userDtoCreate.getEmail())
                .name(userDtoCreate.getName())
                .build();
    }

    public UserDto patch(UserDto userDto) {

        databasePort.patchUser(User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .name(userDto.getName())
                .build());

        return userDto;
    }

    public UserDto changeStatusUser(String email) {
        UserDto userDto = getUserByEmail(email);

        userDto.setActive(!userDto.isActive());
        userDto.setPassword(null);

        databasePort.patchUser(User.builder()
                .id(userDto.getId())
                .active(userDto.isActive()).build());

        return userDto;
    }

    public UserDto login(String email, String password) {
        Optional<User> userOptional = databasePort.getUsers().stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst();

        if (userOptional.isPresent() && userOptional.get().isActive()) {
            User user = userOptional.get();
            if (BCrypt.checkpw(password, user.getPassword())) {
                return UserDto.builder()
                        .id(user.getId())
                        .email(user.getEmail())
                        .name(user.getName())
                        .build();
            } else {
                throw new NotFoundException();
            }
        } else {
            throw new NotFoundException();
        }
    }
}
