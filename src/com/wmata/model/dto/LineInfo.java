package com.wmata.model.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LineInfo {
    @JsonProperty("LineCode")
    private String lineCode;

    @JsonProperty("DisplayName")
    private String displayName;

    @JsonProperty("StartStationCode")
    private String startStationCode;

    @JsonProperty("EndStationCode")
    private String endStationCode;

    @JsonProperty("InternalDestination1")
    private String internalDestination1;

    @JsonProperty("InternalDestination2")
    private String internalDestination2;
}