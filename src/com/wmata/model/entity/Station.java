package com.wmata.model.entity;

import javax.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "stations")
@Data
@NoArgsConstructor
public class Station {
    @Id
    @JsonProperty("Code")
    private String stationCode;

    @JsonProperty("Name")
    private String stationName;

    @JsonProperty("LineCode1")
    @Column(length = 10)
    private String lineCode1;

    @JsonProperty("LineCode2")
    @Column(length = 10)
    private String lineCode2;

    @JsonProperty("LineCode3")
    @Column(length = 10)
    private String lineCode3;

    @JsonProperty("LineCode4")
    @Column(length = 10)
    private String lineCode4;

    @JsonProperty("Lat")
    private Double lat;

    @JsonProperty("Lon")
    private Double lon;

    // 使用 @Embedded 嵌入地址对象
    @Embedded
    @JsonProperty("Address")
    private Address address;

    @JsonProperty("StationTogether1")
    private String stationTogether1;

    @JsonProperty("StationTogether2")
    private String stationTogether2;

    // 将 Address 改为 @Embeddable 类
    @Embeddable
    @Data
    @NoArgsConstructor
    public static class Address {
        @JsonProperty("Street")
        private String street;

        @JsonProperty("City")
        private String city;

        @JsonProperty("State")
        private String state;

        @JsonProperty("Zip")
        private String zip;

        // 辅助方法：获取完整地址
        public String getFullAddress() {
            return String.format("%s, %s, %s %s",
                    street != null ? street : "",
                    city != null ? city : "",
                    state != null ? state : "",
                    zip != null ? zip : "").trim();
        }
    }

    // 辅助方法：获取完整地址
    public String getFullAddress() {
        if (address == null) return "";
        return address.getFullAddress();
    }
}