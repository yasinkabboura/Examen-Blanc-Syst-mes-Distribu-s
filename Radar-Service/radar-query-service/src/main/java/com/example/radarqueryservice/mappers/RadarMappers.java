package com.example.radarqueryservice.mappers;

import com.example.radarqueryservice.entities.OverSpeedDetection;
import com.example.radarqueryservice.entities.Radar;
import coreapi.*;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RadarMappers {
    Radar from(RadarDTO radarDTO);
    RadarResponseDTO from(Radar radar);
    RadarOverSpeedsDTO fromRadar(Radar radar);
    OverSpeedDetection from(OverSpeedRequestDTO overSpeedRequestDTO);
    OverSpeedResponseDTO fromOS(OverSpeedDetection overSpeedRequest);
}
