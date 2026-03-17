package com.wmata.model.dto;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TrainPrediction {
    @JsonProperty("Car")
    private String car;

    @JsonProperty("Destination")
    private String destination;

    @JsonProperty("DestinationCode")
    private String destinationCode;

    @JsonProperty("DestinationName")
    private String destinationName;

    @JsonProperty("Group")
    private String group;

    @JsonProperty("Line")
    private String line;

    @JsonProperty("LocationCode")
    private String locationCode;

    @JsonProperty("LocationName")
    private String locationName;

    @JsonProperty("Min")
    private String min;  // "ARR" (arriving), "BRD" (boarding), or number of minutes

    // 添加这两个字段用于状态标记
    private boolean arriving;
    private boolean boarding;

    // 辅助方法
    public boolean isArriving() {
        return arriving || "ARR".equals(min);
    }

    public boolean isBoarding() {
        return boarding || "BRD".equals(min);
    }

    public int getMinutes() {
        if (isArriving() || isBoarding()) {
            return 0;
        }
        try {
            return Integer.parseInt(min);
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}