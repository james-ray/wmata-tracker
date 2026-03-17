package com.wmata.service;

import com.wmata.model.dto.StationInfo;
import com.wmata.model.entity.Station;
import com.wmata.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StationService {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private WmataApiService wmataApiService;

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }

    public Station getStationByCode(String stationCode) {
        return stationRepository.findById(stationCode).orElse(null);
    }

    public List<Station> getStationsByLine(String lineCode) {
        return stationRepository.findAllByLineCode(lineCode);
    }

    public List<Station> searchStations(String name) {
        return stationRepository.findByStationNameContainingIgnoreCase(name);
    }

    public StationInfo getStationInfo(String stationCode) throws Exception {
        // get from db
        Station station = getStationByCode(stationCode);
        if (station == null) {
            // if not exist in db, get from api
            String apiResponse = wmataApiService.getStationInfo(stationCode);
            // TODO: parse the response and store to db
            return null;
        }

        return convertToStationInfo(station);
    }

    private StationInfo convertToStationInfo(Station station) {
        StationInfo info = new StationInfo();
        info.setStationCode(station.getStationCode());
        info.setStationName(station.getStationName());

        // set all line codes
        info.setLineCode1(station.getLineCode1());
        info.setLineCode2(station.getLineCode2());
        info.setLineCode3(station.getLineCode3());
        info.setLineCode4(station.getLineCode4());

        info.setLat(station.getLat());
        info.setLon(station.getLon());

        // handle embedded address
        if (station.getAddress() != null) {
            Station.Address addressObj = station.getAddress();
            String address = String.format("%s, %s, %s %s",
                    addressObj.getStreet(),
                    addressObj.getCity(),
                    addressObj.getState(),
                    addressObj.getZip());
            info.setAddress(address);
        } else {
            info.setAddress("");
        }

        // handle connected stations
        info.setStationTogether1(station.getStationTogether1());
        info.setStationTogether2(station.getStationTogether2());

        return info;
    }
}