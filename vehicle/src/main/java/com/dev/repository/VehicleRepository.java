package com.dev.repository;

import com.dev.domain.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {

    @Query("select v from Vehicle  v join fetch v.comments where v.id =:id")
    Vehicle fetchVehicleWithComments(@Param("id") String vehicleId);
}
