package com.reddit.Sentiment.sentiment.controller;

import com.reddit.Sentiment.service.RedditSentimentService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class SentimentController {

    private final RedditSentimentService sentimentService;

    public SentimentController(RedditSentimentService sentimentService) {
        this.sentimentService = sentimentService;
    }

    @GetMapping("/sentiment")
    public Map<String, Integer> getSentiment(@RequestParam String topic) {
        return sentimentService.analyzeTopicSentiment(topic);
    }
}