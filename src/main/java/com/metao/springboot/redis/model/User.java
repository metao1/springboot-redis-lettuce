package com.metao.springboot.redis.model;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@RedisHash("User")
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    private String id;

    @Indexed
    private String name;

    @Indexed
    @TimeToLive(unit = TimeUnit.SECONDS)
    private int timeout;
}
