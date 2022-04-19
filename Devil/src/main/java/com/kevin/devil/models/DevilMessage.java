package com.kevin.devil.models;

public class DevilMessage {

    private String userId;
    private String message;
    private long createdAt;
    private long receivedAt;
    private boolean isTyping;
    private boolean fromUser;

    public DevilMessage(String userId, String message, long createdAt, long receivedAt, boolean isTyping, boolean fromUser) {
        this.userId = userId;
        this.message = message;
        this.createdAt = createdAt;
        this.receivedAt = receivedAt;
        this.isTyping = isTyping;
        this.fromUser = fromUser;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getReceivedAt() {
        return receivedAt;
    }

    public void setReceivedAt(long receivedAt) {
        this.receivedAt = receivedAt;
    }

    public boolean isTyping() {
        return isTyping;
    }

    public void setTyping(boolean typing) {
        isTyping = typing;
    }

    public boolean isFromUser() {
        return fromUser;
    }

    public void setFromUser(boolean fromUser) {
        this.fromUser = fromUser;
    }
}
