package com.wmata.controller;

import com.wmata.model.dto.StationInfo;
import com.wmata.model.entity.Station;
import com.wmata.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stations")
@CrossOrigin(origins = "*")
public class StationController {

    @Autowired
    private StationService stationService;

    @GetMapping
    public ResponseEntity<List<Station>> getAllStations() {
        return ResponseEntity.ok(stationService.getAllStations());
    }

    @GetMapping("/{stationCode}")
    public ResponseEntity<Station> getStationByCode(@PathVariable String stationCode) {
        Station station = stationService.getStationByCode(stationCode);
        if (station == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(station);
    }

    @GetMapping("/{stationCode}/info")
    public ResponseEntity<StationInfo> getStationInfo(@PathVariable String stationCode) {
        try {
            StationInfo info = stationService.getStationInfo(stationCode);
            if (info == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(info);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/line/{lineCode}")
    public ResponseEntity<List<Station>> getStationsByLine(@PathVariable String lineCode) {
        return ResponseEntity.ok(stationService.getStationsByLine(lineCode));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Station>> searchStations(@RequestParam String name) {
        return ResponseEntity.ok(stationService.searchStations(name));
    }
}