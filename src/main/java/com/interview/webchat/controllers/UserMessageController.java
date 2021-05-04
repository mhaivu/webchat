package com.interview.webchat.controllers;

import com.interview.webchat.models.Message;
import com.interview.webchat.models.User;
import com.interview.webchat.models.UserMessage;
import com.interview.webchat.payload.GeneralResponse;
import com.interview.webchat.payload.MessageRequest;
import com.interview.webchat.payload.UserMessageRequest;
import com.interview.webchat.repository.MessageRepository;
import com.interview.webchat.repository.UserMessageRepository;
import com.interview.webchat.repository.UserRepository;
import com.interview.webchat.services.UserDetailsImpl;
import javassist.NotFoundException;
import net.bytebuddy.asm.Advice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user/message")
public class UserMessageController {

    private final static Logger log = LoggerFactory.getLogger(UserMessageController.class);

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserMessageRepository userMessageRepository;

    @Autowired
    UserRepository userRepository;

    @PutMapping("/{message_id}")
    public ResponseEntity<?> updateUserMessage(@PathVariable(value = "message_id") Long message_id, UserMessageRequest dataRequest, Authentication authentication) throws NotFoundException{
        User user = getCurrentUser(authentication);

        Message message = messageRepository.findByMessageId(message_id)
                .orElseThrow(() -> new NotFoundException("Message Not Found with id = " + message_id));

        UserMessage userMessage;

        if (userMessageRepository.existsByUserAndMessage(user, message)){
            userMessage = userMessageRepository.findByUserAndMessage(user, message);
        } else {
            userMessage= new UserMessage(user, message);
        }

        userMessage.setDisliked(dataRequest.isDisliked());
        userMessage.setLiked(dataRequest.isLiked());

        userMessageRepository.save(userMessage);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralResponse(String.format("The message with id %d is updated", message_id)));
    }

    private User getCurrentUser(Authentication authentication) throws UsernameNotFoundException{
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findUserByUserId(userDetails.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + userDetails.getEmail()));
        return user;
    }
}
