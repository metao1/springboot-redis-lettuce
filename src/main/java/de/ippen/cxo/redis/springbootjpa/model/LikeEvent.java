package de.ippen.cxo.redis.springbootjpa.model;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Date;

@Builder
@EqualsAndHashCode(callSuper = true)
public class LikeEvent extends Event {
    private final long id;
    private final long srcUserId;
    private final long dstUserId;
    private final int count;

    public LikeEvent(int id, int userId, int dstUserId, int count) {
        super(new Date().getTime());
        this.id = id;
        this.srcUserId = userId;
        this.dstUserId = dstUserId;
        this.count = count;
    }
}
