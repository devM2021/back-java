package com.dev.services;

import com.dev.domain.Vehicle;
import com.dev.exeptions.AppResponseEntityException;
import com.dev.repository.VehicleRepository;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl extends AbstractCommonServiceImpl<Vehicle, String, VehicleRepository>
        implements VehicleService {

    private final VehicleRepository vehicleRepository;

    protected VehicleServiceImpl(VehicleRepository vehicleRepository) {
        super("Vehicle");
        this.vehicleRepository = vehicleRepository;
    }

    @Override
    public VehicleRepository repository() {
        return vehicleRepository;
    }

    @Override
    public Vehicle validate(Vehicle entity) {
        if (entity.getName() == null || entity.getName().isEmpty())
            throw new AppResponseEntityException("Vehicle name cannot be null");
        return entity;
    }

    @Override
    public Vehicle fetchVehicleWithComments(String vehicleId) {
        return vehicleRepository.fetchVehicleWithComments(vehicleId);
    }
}
