package com.interview.webchat.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.webchat.models.Report;
import com.interview.webchat.models.User;
import com.interview.webchat.repository.UserMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {

    final static Logger logger = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    UserMessageRepository userMessageRepository;

    private ObjectMapper mapper = new ObjectMapper();

    public List<Report> getActiveUsers(int top, int minOfMessages){
        List<Map<String,Object>> result = userMessageRepository.getUsersAndNumberOfMessages();
        if (minOfMessages >=0){
            result = userMessageRepository.getUsersWithMinOfMessages(minOfMessages);
        } else if (top >0){
             result = userMessageRepository.getTopUsers(top);

        };

        if (result.size() == 0) return new ArrayList<Report>();

        List<Report> resultList = result.stream()
                .map(o -> {
                    try {
                        return
                                mapper.readValue(mapper.writeValueAsString(o),Report.class);
                    } catch (Exception e) {
                        logger.error(e.getMessage(),e);
                    }
                    return null;
                }).collect(Collectors.toList());

        return resultList;
    }
}
