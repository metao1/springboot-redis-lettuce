package com.metao.springboot.redis.model;

import java.util.Date;

import lombok.Builder;
import lombok.EqualsAndHashCode;

@Builder
@EqualsAndHashCode(callSuper = true)
public class LikeEvent extends Event {
    private final long id;
    private final String srcUserId;
    private final String dstUserId;
    private final long count;

    public LikeEvent(long id, String userId, String dstUserId, long count) {
        super(new Date().getTime());
        this.id = id;
        this.srcUserId = userId;
        this.dstUserId = dstUserId;
        this.count = count;
    }
}
