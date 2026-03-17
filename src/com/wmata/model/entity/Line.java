package com.wmata.model.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lines")
@Data
@NoArgsConstructor
public class Line {
    @Id
    private String lineCode;

    private String displayName;
    private String startStationCode;
    private String endStationCode;
    private String internalDestination1;
    private String internalDestination2;
}