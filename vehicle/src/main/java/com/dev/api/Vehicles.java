package com.dev.api;

import com.dev.domain.Vehicle;
import com.dev.services.VehicleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("vehicles")
@Slf4j
public class Vehicles {

    private final VehicleService vehicleService;

    public Vehicles(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("list")
    public ResponseEntity<List<Vehicle>> list() {
        return new ResponseEntity<>(vehicleService.fetchAllRecord(), HttpStatus.ACCEPTED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Vehicle> findOne(@PathVariable String id) {
        return new ResponseEntity<>(vehicleService.fetchOneRecordById(id), HttpStatus.ACCEPTED);
    }
}
