package com.meta.springbootjpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.io.Serializable;

@Data
@Builder
@RedisHash("User")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    private String id;

    public enum Gender {
        MALE, FEMALE
    }

    private String name;
    @Indexed
    private Gender gender;
    @Indexed
    private int grade;
}
