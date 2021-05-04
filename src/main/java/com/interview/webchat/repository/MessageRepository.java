package com.interview.webchat.repository;

import com.interview.webchat.models.Message;
import com.interview.webchat.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {
    Optional<Message> findByMessageId(Long messageId);
}
