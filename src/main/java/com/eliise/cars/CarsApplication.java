package com.eliise.cars;

import com.eliise.cars.dao.CarDao;
import com.eliise.cars.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

import static com.eliise.cars.domain.Car.Fuel.BENZINE;
import static com.eliise.cars.domain.Car.Gearbox.MANUAL;

@SpringBootApplication
public class CarsApplication implements CommandLineRunner {
//esimene
	public static void main(String[] args) {
		SpringApplication.run(CarsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Car> cars = carDao.selectAllCars();

		/*Car car = new Car();
		car.setCarBrand("BMW");
		car.setCategorySign("M1");
		car.setEnginePower(110);
		car.setColor("RED");
		car.setGearbox(MANUAL);
		car.setFuel(BENZINE);

		carDao.insertCar(car);*/

		UUID carUid = UUID.fromString("5aa4d0b8-b5f0-459d-928e-e853e57068f7");
		Car car1 = carDao.selectCarByCarUid(carUid);
		System.out.printf("");

		UUID uuid = UUID.fromString("5aa4d0b8-b5f0-459d-928e-e853e57068f7");
		carDao.deleteCarByCarUid(uuid);

		UUID test = UUID.fromString("bcc34ffd-56b8-43e0-83e0-4640c630ab52");
		carDao.deleteCarByCarUid(test);
	}

	@Autowired
	private CarDao carDao;
}
