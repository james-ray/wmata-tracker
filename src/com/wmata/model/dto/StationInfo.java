package com.wmata.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StationInfo {
    @JsonProperty("Code")
    private String stationCode;

    @JsonProperty("Name")
    private String stationName;

    @JsonProperty("LineCode1")
    private String lineCode1;

    @JsonProperty("LineCode2")
    private String lineCode2;

    @JsonProperty("LineCode3")
    private String lineCode3;

    @JsonProperty("LineCode4")
    private String lineCode4;

    @JsonProperty("Lat")
    private Double lat;

    @JsonProperty("Lon")
    private Double lon;

    private String address;

    @JsonProperty("StationTogether1")
    private String stationTogether1;

    @JsonProperty("StationTogether2")
    private String stationTogether2;

    // Auxiliary method: get all non-empty line codes
    public String[] getValidLineCodes() {
        java.util.ArrayList<String> codes = new java.util.ArrayList<>();
        if (lineCode1 != null && !lineCode1.isEmpty()) codes.add(lineCode1);
        if (lineCode2 != null && !lineCode2.isEmpty()) codes.add(lineCode2);
        if (lineCode3 != null && !lineCode3.isEmpty()) codes.add(lineCode3);
        if (lineCode4 != null && !lineCode4.isEmpty()) codes.add(lineCode4);
        return codes.toArray(new String[0]);
    }

    // Auxiliary method: get connected stations
    public String[] getConnectedStations() {
        java.util.ArrayList<String> connected = new java.util.ArrayList<>();
        if (stationTogether1 != null && !stationTogether1.isEmpty()) connected.add(stationTogether1);
        if (stationTogether2 != null && !stationTogether2.isEmpty()) connected.add(stationTogether2);
        return connected.toArray(new String[0]);
    }
}