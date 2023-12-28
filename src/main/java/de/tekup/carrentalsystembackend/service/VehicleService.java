package de.tekup.carrentalsystembackend.service;

import de.tekup.carrentalsystembackend.dto.VehicleDto;
import de.tekup.carrentalsystembackend.model.CarBrand;
import de.tekup.carrentalsystembackend.model.User;
import de.tekup.carrentalsystembackend.model.Vehicle;
import de.tekup.carrentalsystembackend.repository.UserRepository;
import de.tekup.carrentalsystembackend.repository.VehicleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {
    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private UserRepository userRepository;


    public Vehicle findVehicleById(Long id) {
        return vehicleRepository.findVehicleByIdVehicle(id).orElse(null);
    }
    public void addVehicle(Long idUser, VehicleDto vehicleDto) {
        // Retrieve the user from the database
        User user = userRepository.findById(idUser)
                .orElseThrow(() -> new EntityNotFoundException("User not found"));

        // Create new vehicle instance from the dto
        Vehicle vehicle =  convertDtoToInstance(vehicleDto);
        vehicle.setUser(user);
        vehicleRepository.save(vehicle);

        // Add the vehicle to the user's list of vehicles
        user.getVehicles().add(vehicle);
        userRepository.save(user);

    }

    public Vehicle getVehicleById(Long id)
    {
        return vehicleRepository.findById(id).get();
    }

    public Vehicle updateVehicle(Vehicle vehicle)
    {
        return vehicleRepository.saveAndFlush(vehicle);
    }


    private Vehicle convertDtoToInstance(VehicleDto vehicleDto) {
        Vehicle vehicle= new Vehicle();

        vehicle.setLicensePlateNumber(vehicleDto.getLicensePlateNumber());
        vehicle.setBrand(vehicleDto.getBrand());
        vehicle.setModel(vehicleDto.getModel());
        vehicle.setLaunchYear(vehicleDto.getLaunchYear());
        vehicle.setColor(vehicleDto.getColor());
        vehicle.setMileage(vehicleDto.getMileage());
        vehicle.setFuel(vehicleDto.getFuel());
        vehicle.setTransmissionType(vehicleDto.getTransmissionType());
        vehicle.setHorsPower(vehicleDto.getHorsPower());
        vehicle.setPricePerDay(vehicleDto.getPricePerDay());
        vehicle.setLastMaintenanceMileage(vehicleDto.getLastMaintenanceMileage());
        vehicle.setIsAvailable(vehicleDto.getIsAvailable());

        return vehicle;
    }


    public List<Vehicle> getAllVehicle() {
        return vehicleRepository.findAll();
    }

    public Optional<List<Vehicle>> findAllByBrand(CarBrand brand) {
        return vehicleRepository.findAllByBrand(brand);
    }

    public Optional<List<Vehicle>> findAllByBrandAndModel(CarBrand brand, String model) {
        return vehicleRepository.findAllByBrandAndModel(brand, model);
    }

    public Optional<List<Vehicle>> findAllByFuel(String fuel) {
        return vehicleRepository.findAllByFuel(fuel);
    }



    public Optional<List<Vehicle>> findAllByTransType(String transmissionType) {
        return vehicleRepository.findAllByTransmissionType(transmissionType);
    }

    public Optional<List<Vehicle>> findAllByHorsPower(int horsPower) {
        return vehicleRepository.findAllByHorsPower(horsPower);
    }

    public Optional<List<Vehicle>> findAllByPrice(int pricePerDay) {
        return vehicleRepository.findAllByPricePerDay(pricePerDay);
    }

    public Optional<List<Vehicle>> findAllByAvailability(boolean isAvailable) {
        return  vehicleRepository.findAllByIsAvailable(isAvailable);
    }


}
