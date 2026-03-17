package com.wmata.service;

import com.wmata.model.entity.Line;
import com.wmata.repository.LineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class LineService {

    @Autowired
    private LineRepository lineRepository;

    @Autowired
    private WmataApiService wmataApiService;

    public List<Line> getAllLines() {
        return lineRepository.findAll();
    }

    public Line getLineByCode(String lineCode) {
        return lineRepository.findById(lineCode).orElse(null);
    }

    public void refreshLinesFromApi() throws Exception {
        var response = wmataApiService.getAllLines();
        // TODO: Parse the response and store to db
    }
}