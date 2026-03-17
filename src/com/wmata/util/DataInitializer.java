package com.wmata.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wmata.model.entity.Line;
import com.wmata.model.entity.Station;
import com.wmata.model.response.WmataLineResponse;
import com.wmata.model.response.WmataStationResponse;
import com.wmata.repository.LineRepository;
import com.wmata.repository.StationRepository;
import com.wmata.service.WmataApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private WmataApiService wmataApiService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void run(String... args) throws Exception {
        // init line data
        initLines();

        // init station data
        initStations();
    }

    private void initLines() throws Exception {
        if (lineRepository.count() == 0) {
            WmataLineResponse response = wmataApiService.getAllLines();
            Line redLine = new Line();
            redLine.setLineCode("RD");
            redLine.setDisplayName("Red");
            lineRepository.save(redLine);

            Line blueLine = new Line();
            blueLine.setLineCode("BL");
            blueLine.setDisplayName("Blue");
            lineRepository.save(blueLine);

            // We can add more lines if needed
        }
    }

    private void initStations() throws Exception {
        if (stationRepository.count() == 0) {
            // 初始化Blue Line的车站
            WmataStationResponse response = wmataApiService.getStationsByLine("BL");
            for (Station station : response.getStations()) {
                stationRepository.save(station);
            }

            response = wmataApiService.getStationsByLine("RD");
            for (Station station : response.getStations()) {
                stationRepository.save(station);
            }
        }
    }
}