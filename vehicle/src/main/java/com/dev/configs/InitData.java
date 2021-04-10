package com.dev.configs;

import com.dev.domain.Vehicle;
import com.dev.services.VehicleService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Stream;

@Component
public class InitData {
    private final VehicleService vehicleService;

    public InitData(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostConstruct
    void initVehicle() {
        List<Vehicle> vehicleList = this.vehicleService.fetchAllRecord();
        if (vehicleList.isEmpty()) {
            Stream.of(Vehicle.builder().name("Vehicule 1").description("Description 1").build(),
                    Vehicle.builder().name("Vehicule 2").description("Description 2").build(),
                    Vehicle.builder().name("Vehicule 3").description("Description 3").build(),
                    Vehicle.builder().name("Vehicule 4").description("Description 4").build())
                    .forEach(this.vehicleService::createOrUpdate);
        }
    }
}
