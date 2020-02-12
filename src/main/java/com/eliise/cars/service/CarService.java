package com.eliise.cars.service;

import com.eliise.cars.dao.CarDao;
import com.eliise.cars.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

//viies

@Service
public class CarService {

    private CarDao carDao;

    @Autowired
    public CarService(CarDao carDao) {
        this.carDao = carDao;

    }

    public List<Car> getAllCars() {
        return carDao.selectAllCars();
    }


    public Optional<Car> getCar(UUID carUid) {

        Car car = carDao.selectCarByCarUid(carUid);
        return Optional.ofNullable(car);


    }

    public UUID insertCar(Car car) {
        UUID carUid = car.getCarUid() == null ? UUID.randomUUID() : car.getCarUid();
        return carDao.insertCar(Car.newCar(carUid, car));
    }

    public int removeCar(UUID uid) {
        UUID carUid = getCar(uid)
                .map(Car::getCarUid)
                .orElseThrow(() -> new NotFoundException("car " + uid + " not found"));
        return carDao.deleteCarByCarUid(carUid);
    }
}
