package com.wmata.controller;

import com.wmata.model.entity.Line;
import com.wmata.service.LineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lines")
@CrossOrigin(origins = "*")
public class LineController {

    @Autowired
    private LineService lineService;

    @GetMapping
    public ResponseEntity<List<Line>> getAllLines() {
        return ResponseEntity.ok(lineService.getAllLines());
    }

    @GetMapping("/{lineCode}")
    public ResponseEntity<Line> getLineByCode(@PathVariable String lineCode) {
        Line line = lineService.getLineByCode(lineCode);
        if (line == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(line);
    }
}