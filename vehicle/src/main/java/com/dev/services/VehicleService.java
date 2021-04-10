package com.dev.services;

import com.dev.domain.Vehicle;

public interface VehicleService extends CommonService<Vehicle, String> {

    Vehicle fetchVehicleWithComments(String vehicleId);
}
