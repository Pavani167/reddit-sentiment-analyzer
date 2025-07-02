package com.reddit.Sentiment.service;

import org.json.*;
import org.springframework.stereotype.Service;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.comprehend.ComprehendClient;
import software.amazon.awssdk.services.comprehend.model.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.*;

@Service
public class RedditSentimentService {

    private final ComprehendClient comprehendClient;

    public RedditSentimentService() {
        // Same as your standalone example: configure region and credentials
        this.comprehendClient = ComprehendClient.builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
    }

    public Map<String, Integer> analyzeTopicSentiment(String topic) {
        Map<String, Integer> sentimentMap = new HashMap<>();
        sentimentMap.put("POSITIVE", 0);
        sentimentMap.put("NEGATIVE", 0);
        sentimentMap.put("NEUTRAL", 0);

        try {
            String apiUrl = "https://www.reddit.com/search.json?q=" + URLEncoder.encode(topic, "UTF-8");
            HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder json = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) json.append(line);
            reader.close();

            JSONObject data = new JSONObject(json.toString()).getJSONObject("data");
            JSONArray children = data.getJSONArray("children");

            for (int i = 0; i < Math.min(25, children.length()); i++) {
                String text = children.getJSONObject(i).getJSONObject("data").getString("title");

                DetectSentimentRequest request = DetectSentimentRequest.builder()
                        .text(text)
                        .languageCode("en")
                        .build();

                DetectSentimentResponse result = comprehendClient.detectSentiment(request);

                String sentiment = result.sentimentAsString().toUpperCase();
                if (sentimentMap.containsKey(sentiment)) {
                    sentimentMap.put(sentiment, sentimentMap.get(sentiment) + 1);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return sentimentMap;
    }
}
