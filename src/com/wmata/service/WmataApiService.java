package com.wmata.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmata.model.response.WmataLineResponse;
import com.wmata.model.response.WmataStationResponse;
import com.wmata.model.response.WmataTrainResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

@Service
public class WmataApiService {

    @Value("${wmata.api.key}")
    private String apiKey;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private String makeApiCall(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestProperty("Cache-Control", "no-cache");
        connection.setRequestProperty("api_key", apiKey);
        connection.setRequestMethod("GET");

        int status = connection.getResponseCode();
        if (status != 200) {
            throw new RuntimeException("API call failed with status: " + status);
        }

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            return reader.lines().collect(Collectors.joining());
        } finally {
            connection.disconnect();
        }
    }

    @Cacheable(value = "trainPredictions", key = "#stationCode")
    public WmataTrainResponse getTrainPredictions(String stationCode) throws Exception {
        String url = "https://api.wmata.com/StationPrediction.svc/json/GetPrediction/" + stationCode;
        String response = makeApiCall(url);
        return objectMapper.readValue(response, WmataTrainResponse.class);
    }

    public WmataLineResponse getAllLines() throws Exception {
        String url = "https://api.wmata.com/Rail.svc/json/jLines";
        String response = makeApiCall(url);
        return objectMapper.readValue(response, WmataLineResponse.class);
    }

    public WmataStationResponse getStationsByLine(String lineCode) throws Exception {
        String url = "https://api.wmata.com/Rail.svc/json/jStations?LineCode=" + lineCode;
        String response = makeApiCall(url);
        return objectMapper.readValue(response, WmataStationResponse.class);
    }

    @Cacheable(value = "stationInfo", key = "#stationCode")
    public String getStationInfo(String stationCode) throws Exception {
        String url = "https://api.wmata.com/Rail.svc/json/jStationInfo?StationCode=" + stationCode;
        return makeApiCall(url);
    }
}