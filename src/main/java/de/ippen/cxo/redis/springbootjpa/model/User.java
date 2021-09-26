package de.ippen.cxo.redis.springbootjpa.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@Builder
@RedisHash("User")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    private long id;

    public enum Gender {
        MALE, FEMALE
    }

    private String name;
    private Gender gender;
    private int grade;
}
