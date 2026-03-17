package com.wmata.service;

import com.wmata.model.dto.TrainPrediction;
import com.wmata.model.response.WmataTrainResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TrainService {

    @Autowired
    private WmataApiService wmataApiService;

    @Autowired
    private StationService stationService;

    public List<TrainPrediction> getPredictionsForStation(String stationCode) throws Exception {
        WmataTrainResponse response = wmataApiService.getTrainPredictions(stationCode);

        return response.getTrains().stream()
                .map(this::enrichTrainPrediction)
                .collect(Collectors.toList());
    }

    public List<TrainPrediction> getPredictionsForLine(String lineCode) throws Exception {
        // 获取该线路的所有车站
        var stations = stationService.getStationsByLine(lineCode);

        // 获取所有车站的预测（这里简化处理，实际可能需要并行调用）
        return stations.stream()
                .flatMap(station -> {
                    try {
                        return getPredictionsForStation(station.getStationCode()).stream();
                    } catch (Exception e) {
                        return java.util.stream.Stream.empty();
                    }
                })
                .filter(prediction -> lineCode.equals(prediction.getLine()))
                .collect(Collectors.toList());
    }

    private TrainPrediction enrichTrainPrediction(TrainPrediction prediction) {
        // 处理特殊时间值
        String min = prediction.getMin();
        if ("ARR".equals(min)) {
            prediction.setArriving(true);
            prediction.setMin("0");
        } else if ("BRD".equals(min)) {
            prediction.setBoarding(true);
            prediction.setMin("0");
        }
        return prediction;
    }
}