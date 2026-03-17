package com.wmata.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wmata.model.dto.TrainPrediction;
import lombok.Data;
import java.util.List;

@Data
public class WmataTrainResponse {
    @JsonProperty("Trains")
    private List<TrainPrediction> trains;
}