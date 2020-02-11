package com.eliise.cars.dao;

import com.eliise.cars.domain.Car;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

class FakeDataDaoTest {

    private FakeDataDao fakeDataDao;

    @BeforeEach
    void setUp() {
        fakeDataDao = new FakeDataDao();
    }

    @Test
    void shouldSelectAllCars() throws Exception {
        List<Car> cars = fakeDataDao.selectAllCars();
        assertThat(cars).hasSize(1);
        Car car = cars.get(0);
        assertThat(car.getCarBrand()).isEqualTo("BMW");
        assertThat(car.getCategorySign()).isEqualTo("M1");
        assertThat(car.getColor()).isEqualTo("red");
        assertThat(car.getEnginePower()).isEqualTo(110);
        assertThat(car.getFuel()).isEqualTo(Car.Fuel.BENZINE);
        assertThat(car.getGearbox()).isEqualTo(Car.Gearbox.MANUAL);
        assertThat(car.getCarUid()).isNotNull();
    }

    @Test
    void shouldSelectCarByCarUid() throws Exception {
        UUID testCarUid = UUID.randomUUID();
        Car car2 = new Car(testCarUid,
                "BMW", "M1", 105, "red",
                Car.Gearbox.MANUAL, Car.Fuel.DIESEL);
        fakeDataDao.insertCar(testCarUid, car2);
        assertThat(fakeDataDao.selectAllCars()).hasSize(2);
        Optional<Car> optionalCar = fakeDataDao.selectCarByCarUid(testCarUid);
        assertThat(optionalCar.isPresent()).isTrue();
        assertThat(optionalCar.get()).isEqualToComparingFieldByField(car2);
    }

    @Test
    void deleteCarByCarUid() throws Exception {
        UUID m1UserUid = fakeDataDao.selectAllCars().get(0).getCarUid();
        fakeDataDao.deleteCarByCarUid(m1UserUid);
        assertThat(fakeDataDao.selectCarByCarUid(m1UserUid).isPresent()).isFalse();
        assertThat(fakeDataDao.selectAllCars()).isEmpty();
    }

    @Test
    void insertCar() throws Exception {
        UUID carUid = UUID.randomUUID();
        Car car = new Car(carUid,
                "BMW", "M1", 105, "red",
                Car.Gearbox.MANUAL, Car.Fuel.DIESEL);

        fakeDataDao.insertCar(carUid, car);

        List<Car> cars = fakeDataDao.selectAllCars();
        assertThat(cars).hasSize(2);
        assertThat(fakeDataDao.selectCarByCarUid(carUid).get()).isEqualToComparingFieldByField(car);

    }
}