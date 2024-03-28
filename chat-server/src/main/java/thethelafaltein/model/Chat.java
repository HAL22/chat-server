package thethelafaltein.model;

import java.time.*;

public class Chat {
    private long id;
    private long customerId;
    private String message;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private ChatStatus chatStatus;

    public Chat(){

    }

    public Chat(long id, long customerId, String message, LocalDateTime createdAt, LocalDateTime updatedAt, ChatStatus chatStatus){
        this.id = id;
        this.customerId = customerId;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.chatStatus = chatStatus;
    }

    public Chat(long customerId, String message, LocalDateTime createdAt, LocalDateTime updatedAt, ChatStatus chatStatus){
        this.customerId = customerId;
        this.message = message;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.chatStatus = chatStatus;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getMessage() {
        return message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public ChatStatus getChatStatus(){
        return chatStatus;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}


