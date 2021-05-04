package com.interview.webchat.repository;

import com.interview.webchat.models.Message;
import com.interview.webchat.models.User;
import com.interview.webchat.models.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
    UserMessage findByUserAndMessage(User user, Message message);
    Boolean existsByUserAndMessage(User user, Message message);
}
