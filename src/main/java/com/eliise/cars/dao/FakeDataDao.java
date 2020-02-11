package com.eliise.cars.dao;

import com.eliise.cars.domain.Car;
import org.springframework.stereotype.Repository;

import java.util.*;
    //neljas
@Repository
public class FakeDataDao  {

    private Map<UUID, Car> database;

    public FakeDataDao() {
        database = new HashMap<>();
        UUID m1UserUid = UUID.randomUUID();
        database.put(m1UserUid, new Car(m1UserUid, "BMW", "M1",
               110 , "red", Car.Gearbox.MANUAL, Car.Fuel.BENZINE));
    }

    public List<Car> selectAllCars() {
        return new ArrayList<>(database.values());
    }


    public Optional<Car> selectCarByCarUid(UUID carUid) {
        return Optional.ofNullable(database.get(carUid));
    }


    public int deleteCarByCarUid(UUID carUid) {
        database.remove(carUid);
        return 1;
    }

    public int insertCar(UUID carUid, Car car) {
        database.put(carUid, car);
        return 1;
    }
}
