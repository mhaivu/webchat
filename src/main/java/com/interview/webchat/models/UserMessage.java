package com.interview.webchat.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "usermessage")
public class UserMessage {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_message_id")
    private Long userMessageId;

    @Basic
    @Column(name = "isDisliked", nullable = true)
    private boolean isDisliked;

    @Basic
    @Column(name = "isLiked", nullable = true)
    private boolean isLiked;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name="message_id", nullable = false)
    private Message message;

    @Basic
    @Column(name = "created_time")
    private Date createTime;

    public UserMessage() {
    }

    public UserMessage(User user, Message message) {
        this.user = user;
        this.message = message;
    }

    public Long getUserMessageId() {
        return userMessageId;
    }

    public void setUserMessageId(Long userMessageId) {
        this.userMessageId = userMessageId;
    }

    public boolean isDisliked() {
        return isDisliked;
    }

    public void setDisliked(boolean disliked) {
        isDisliked = disliked;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}
