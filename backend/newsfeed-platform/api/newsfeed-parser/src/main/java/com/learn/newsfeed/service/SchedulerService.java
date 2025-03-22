package com.learn.newsfeed.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.services.sqs.SqsClient;

@Service
public class SchedulerService {

    private SqsClient sqsClient;

    public SchedulerService(SqsClient sqsClient) {
        this.sqsClient = sqsClient;
    }

    @Scheduled(cron = "0 0/1 * 1/1 * ? *")
    public void onPollMessages(){

    }
}
