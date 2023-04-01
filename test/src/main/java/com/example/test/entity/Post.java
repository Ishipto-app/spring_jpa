package com.example.test.entity;

import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="post")
public class Post {
    @Id
    private String id;
    private String title;

    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}

