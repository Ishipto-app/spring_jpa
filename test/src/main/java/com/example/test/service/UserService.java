package com.example.test.service;

import com.example.test.dto.UserDto;
import com.example.test.entity.Employee;
import com.example.test.entity.User;
import com.example.test.projection.UserProjection;
import com.example.test.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class UserService {
    @Autowired
    UserRepository userRepository;

    //Method query + Convert to Dto
    public List<UserDto> findAllUsers1() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
            userDtos.add(userDto);
        }
        return userDtos;
    }
    //JPQL
    public List<UserDto> findAllUsers2() {
        return userRepository.findAllUserDto();
    }
    //Native Query
    public List<UserDto> findAllUsers3() {
        List<UserDto> userDtos = userRepository.getUserDtoUsingNativeQuery();
        return userDtos;
    }

    //Projection
    public List<UserDto> findAllUsers4() {
        List<UserProjection> users = userRepository.findAllProjectedBy();
        List<UserDto> userDtos = new ArrayList<>();
        for (UserProjection userProjection : users) {
            UserDto userDto = new UserDto(userProjection.getId(), userProjection.getName(), userProjection.getEmail());
            userDtos.add(userDto);
        }
        return userDtos;
    }
}
