package com.wmata.controller;

import com.wmata.model.dto.TrainPrediction;
import com.wmata.model.entity.Station;
import com.wmata.service.TrainService;
import com.wmata.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/trains")
@CrossOrigin(origins = "*")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private StationService stationService;

    @GetMapping("/predictions/station/{stationCode}")
    public ResponseEntity<List<TrainPrediction>> getPredictionsForStation(
            @PathVariable String stationCode) {
        try {
            List<TrainPrediction> predictions = trainService.getPredictionsForStation(stationCode);
            return ResponseEntity.ok(predictions);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/predictions/line/{lineCode}")
    public ResponseEntity<List<TrainPrediction>> getPredictionsForLine(
            @PathVariable String lineCode) {
        try {
            List<TrainPrediction> predictions = trainService.getPredictionsForLine(lineCode);
            return ResponseEntity.ok(predictions);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/live/{lineCode}")
    public ResponseEntity<?> getLiveTrainsForLine(@PathVariable String lineCode) {
        // 获取某条线路上的实时列车位置
        // 这个API可能需要组合多个数据源
        return ResponseEntity.ok().build();
    }
}