package de.ippen.cxo.redis.springbootjpa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Event {
    private final long timestamp;
}
