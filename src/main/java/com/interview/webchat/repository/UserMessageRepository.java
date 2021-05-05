package com.interview.webchat.repository;

import com.interview.webchat.models.Message;
import com.interview.webchat.models.Report;
import com.interview.webchat.models.User;
import com.interview.webchat.models.UserMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface UserMessageRepository extends JpaRepository<UserMessage, Long> {
    UserMessage findByUserAndMessage(User user, Message message);
    Boolean existsByUserAndMessage(User user, Message message);

    @Query(
            value = "select u.user_id as user_id, u.email as email, count(um.message_id) as number from `user` u join usermessage um on u.user_id = um.user_id " +
                    "group by u.user_id ",
            nativeQuery = true
    )
    List<Map<String,Object>> getUsersAndNumberOfMessages();

    @Query(
            value = "select u.user_id as user_id, u.email as email, count(um.message_id) as number from `user` u join usermessage um on u.user_id = um.user_id " +
                    "group by u.user_id " +
                    "order by number DESC " +
                    "limit :top",
            nativeQuery = true
    )
    List<Map<String,Object>> getTopUsers(@Param("top") int top);

    @Query(
            value = "select * from (select u.user_id as user_id, u.email as email, count(um.message_id) as number from `user` u join usermessage um on u.user_id = um.user_id " +
                    "group by u.user_id) as newtb " +
                    "where number >= :min",
            nativeQuery = true
    )
    List<Map<String,Object>> getUsersWithMinOfMessages(@Param("min") int min);
}
