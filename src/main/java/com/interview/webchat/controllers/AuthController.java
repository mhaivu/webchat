package com.interview.webchat.controllers;

import com.interview.webchat.models.User;
import com.interview.webchat.payload.LoginRequest;
import com.interview.webchat.payload.LoginResponse;
import com.interview.webchat.payload.GeneralResponse;
import com.interview.webchat.payload.RegisterRequest;
import com.interview.webchat.repository.UserRepository;
import com.interview.webchat.security.JwtUtils;
import com.interview.webchat.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final static Logger log = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) throws NoSuchAlgorithmException{

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), generatePassword(loginRequest.getEmail()))
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(new LoginResponse(jwt, userDetails.getId(), userDetails.getEmail()));
    }

    @PostMapping("register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) throws NoSuchAlgorithmException {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new GeneralResponse("Existing email!"));
        }

        User user = new User(registerRequest.getFistName(), registerRequest.getLastName(), registerRequest.getEmail(), encoder.encode(generatePassword(registerRequest.getEmail())));

        userRepository.save(user);
        return ResponseEntity.ok(new GeneralResponse("User registered successfully."));
    }

    private String generatePassword(String username) throws NoSuchAlgorithmException {
        final String MD5_SECRET = "3294kfsdf%skfdkjds";

        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(MD5_SECRET.getBytes());

        byte[] messageDigest = md.digest(username.getBytes());

        BigInteger no = new BigInteger(1, messageDigest);

        // Convert message digest into hex value
        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}
