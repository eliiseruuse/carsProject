package com.eliise.cars;

import com.eliise.cars.dao.CarDao;
import com.eliise.cars.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

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

		Car car = new Car();
		car.setCarBrand("BMW");
		car.setCategorySign("M1");
		car.setEnginePower(110);
		car.setColor("RED");
		car.setGearbox(MANUAL);
		car.setFuel(BENZINE);

		carDao.insertCar(car);


	}

	@Autowired
	private CarDao carDao;
}
