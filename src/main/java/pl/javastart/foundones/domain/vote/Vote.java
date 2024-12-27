package pl.javastart.foundones.domain.vote;

import java.time.LocalDateTime;

public class Vote {
    private Integer discoveryId;
    private Integer userId;
    private Type type;
    private LocalDateTime dateAdded;

    public Vote(Integer discoveryId, Integer userId, Type type, LocalDateTime dateAdded) {
        this.discoveryId = discoveryId;
        this.userId = userId;
        this.type = type;
        this.dateAdded = dateAdded;
    }

    public Integer getDiscoveryId() {
        return discoveryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }
    public enum Type {
        UP, DOWN
    }
}

