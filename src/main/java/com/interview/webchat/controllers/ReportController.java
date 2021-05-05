package com.interview.webchat.controllers;

import com.interview.webchat.models.Report;
import com.interview.webchat.models.User;
import com.interview.webchat.repository.UserMessageRepository;
import com.interview.webchat.repository.UserRepository;
import com.interview.webchat.services.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/report")
public class ReportController {

    @Autowired
    ReportService reportService;

    @GetMapping("/users")
    public ResponseEntity<?> getActiveUsers(@RequestParam(defaultValue = "-1") int top, @RequestParam(defaultValue = "-1", value = "min") int minOfMessages){

        List<Report> result = reportService.getActiveUsers(top, minOfMessages);
        return ResponseEntity.ok(result);
    }
}
