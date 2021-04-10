package com.dev.services;


import com.dev.config.UnitTestConfig;
import com.dev.domain.Vehicle;
import com.dev.exeptions.AppResponseEntityException;
import com.dev.repository.VehicleRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = {UnitTestConfig.class})
@Slf4j
@Transactional
class VehicleServiceImplTest {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    private static final String VEHICLE_NAME = "VEHICULE 1";
    Vehicle vehicleInstance = Vehicle.builder().name(VEHICLE_NAME).description("TEST VEHICULE").build();
    private String vehicleId;

    @BeforeEach
    void setUp() {
        Vehicle affectation = vehicleService.createOrUpdate(vehicleInstance);
        vehicleId = affectation.getId();
    }

    @AfterEach
    void tearDown() {
        this.vehicleService.removeRecordById(vehicleId, false);
    }

    @Test
    void fetchOneRecordById() {
        Vehicle vehicle = this.vehicleService.fetchOneRecordById(vehicleId);
        assertNotNull(vehicle);
        assertEquals(VEHICLE_NAME, vehicle.getName());
    }

    @Test
    void removeRecordById() {
        Vehicle vehicle = this.vehicleService.fetchOneRecordById(vehicleId);
        this.vehicleService.removeRecordById(vehicleId, true);
        assertNotNull(vehicle);
        assertTrue(vehicle.isDeleted());
    }

    @Test
    void fetchAllRecord() {
        List<Vehicle> vehicles = this.vehicleService.fetchAllRecord();
        assertFalse(vehicles.isEmpty());
        assertEquals(vehicles.size(), 5); // 4 records added when starting
        assertEquals(VEHICLE_NAME, vehicles.get(4).getName());
    }

    @Test
    void repository() {
        assertNotNull(vehicleRepository);
    }

    @Test
    void validate() {
        Vehicle vehicle = this.vehicleService.validate(vehicleInstance);
        assertEquals(VEHICLE_NAME, vehicle.getName());

        vehicle.setName("");
        Exception exception = assertThrows(AppResponseEntityException.class, () -> this.vehicleService.validate(vehicle));
        String msgError = exception.getMessage();
        assertTrue(msgError.contains("Vehicle name cannot be null"));
    }


}