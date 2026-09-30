package com.LearningSB.JournalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class WeatherResponse{
    private Current current;
    @Data
    public class Current{
        @JsonProperty("observation_time")
        private String observationTime;

        private int temperature;

        private List<String> weather_descriptions;

        @JsonProperty("wind_speed")
        private int windSpeed;

        @JsonProperty("wind_dir")
        private String windDir;

        private int pressure;

        private int precip;

        private int humidity;

        private int feelslike;
    }
}


