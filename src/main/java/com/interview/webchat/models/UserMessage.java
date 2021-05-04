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
    @Column(name = "isDisliked")
    private boolean isDislike;

    @Basic
    @Column(name = "isLiked")
    private boolean isLike;

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

    public Long getUserMessageId() {
        return userMessageId;
    }

    public void setUserMessageId(Long userMessageId) {
        this.userMessageId = userMessageId;
    }

    public boolean isDislike() {
        return isDislike;
    }

    public void setDislike(boolean dislike) {
        isDislike = dislike;
    }

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
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
