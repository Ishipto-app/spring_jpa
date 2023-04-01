package com.example.test.repository;

import com.example.test.dto.UserDto;
import com.example.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import com.example.test.projection.UserProjection;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select new demojpa.demojpa.dto.UserDto(u.id, u.name, u.email) from User u")
    List<UserDto> findAllUserDto();

    @Query(nativeQuery = true, name = "getUserDtoUsingNativeQuery")
    List<UserDto> getUserDtoUsingNativeQuery();

    List<UserProjection> findAllProjectedBy();

}