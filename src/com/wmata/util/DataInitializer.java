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
        // 初始化线路数据
        initLines();

        // 初始化车站数据
        initStations();
    }

    private void initLines() throws Exception {
        if (lineRepository.count() == 0) {
            WmataLineResponse response = wmataApiService.getAllLines();
            // 这里需要根据实际的API响应格式来解析
            // 为了简化，我先手动添加一些线路
            Line redLine = new Line();
            redLine.setLineCode("RD");
            redLine.setDisplayName("Red");
            lineRepository.save(redLine);

            Line blueLine = new Line();
            blueLine.setLineCode("BL");
            blueLine.setDisplayName("Blue");
            lineRepository.save(blueLine);

            // 可以继续添加其他线路
        }
    }

    private void initStations() throws Exception {
        if (stationRepository.count() == 0) {
            // 初始化Blue Line的车站
            WmataStationResponse response = wmataApiService.getStationsByLine("BL");
            for (Station station : response.getStations()) {
                stationRepository.save(station);
            }

            // 可以继续初始化其他线路的车站
        }
    }
}