package com.interview.webchat.controllers;

import com.interview.webchat.models.Message;
import com.interview.webchat.models.User;
import com.interview.webchat.payload.GeneralResponse;
import com.interview.webchat.payload.LoginResponse;
import com.interview.webchat.payload.MessageRequest;
import com.interview.webchat.repository.MessageRepository;
import com.interview.webchat.repository.UserRepository;
import com.interview.webchat.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/message")
public class MessageController {

    private final static Logger log = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("")
    public ResponseEntity<?> createMessage(@RequestBody MessageRequest messageRequest, Authentication authentication){
        User user = getCurrentUser(authentication);

        Message newMessage = new Message();
        newMessage.setContent(messageRequest.getContent());
        newMessage.setOwner(user);

        messageRepository.save(newMessage);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new GeneralResponse("The new message is created"));
    }

    @GetMapping("")
    public ResponseEntity<?> getMessages(){
        return null;
    }

    private User getCurrentUser(Authentication authentication) throws UsernameNotFoundException{
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userRepository.findUserByUserId(userDetails.getId())
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + userDetails.getEmail()));
        return user;
    }
}
