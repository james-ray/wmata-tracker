package com.wmata.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.wmata.model.dto.LineInfo;
import lombok.Data;
import java.util.List;

@Data
public class WmataLineResponse {
    @JsonProperty("Lines")
    private List<LineInfo> lines;
}