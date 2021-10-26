package com.meta.springbootjpa.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Date;

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
