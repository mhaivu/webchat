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
@Table(name = "message")
public class Message {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "message_id")
    private Long messageId;

    @Basic
    @Column(name = "content")
    private String content;

    @ManyToOne
    @JoinColumn(name="user_id", nullable = false)
    private User owner;

    @Basic
    @Column(name = "created_time")
    private Date createTime;

    public Message() {
    }

    public Long getUserId() {
        return messageId;
    }

    public void setUserId(Long id) {
        this.messageId = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }
}
