package com.eliise.cars.controller;

import com.eliise.cars.domain.Car;
import com.eliise.cars.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping(
        path = "/api/cars"
)
public class CarsController {

    private final CarService carService;

    public CarsController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> fetchCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{carUid}")
    public ResponseEntity<?> fetchCar(@PathVariable("carUid") UUID carUid) {
        Optional<Car> carOptional = carService.getCar(carUid);
        if (carOptional.isPresent()) {
            return ResponseEntity.ok(carOptional.get());
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorMessage("car " + carUid + " not found"));
    }

    @PostMapping
    public ResponseEntity<UUID> insertNewCar(@RequestBody Car car) {
        UUID carUid = carService.insertCar(car);
        return ResponseEntity.ok().body(carUid);
    }

    @DeleteMapping("/{userUid}")
    public ResponseEntity<Integer> deleteCar(@PathVariable("userUid") UUID carUid) {
        int result = carService.removeCar(carUid);
        return getIntegerResponseEntity(result);
    }

    private ResponseEntity<Integer> getIntegerResponseEntity(int result) {
        if (result == 1) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.badRequest().build();
    }

}


