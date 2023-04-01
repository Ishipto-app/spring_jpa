package com.example.test.entity;

import com.example.test.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "userInfo",
                classes = @ConstructorResult(
                        targetClass = UserDto.class,
                        columns = {
                                @ColumnResult(name = "id", type = Integer.class),
                                @ColumnResult(name = "name", type = String.class),
                                @ColumnResult(name = "email", type=String.class)
                        }
                )
        )
})
@NamedNativeQuery(
        name = "getUserDtoUsingNativeQuery",
        resultSetMapping = "userInfo",
        query = "select u.id, u.name, u.email from user u")
@Getter
@Setter
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
}