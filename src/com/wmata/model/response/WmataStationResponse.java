package com.wmata.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wmata.model.entity.Station;
import lombok.Data;
import java.util.List;

@Data
public class WmataStationResponse {
    @JsonProperty("Stations")
    private List<Station> stations;
}