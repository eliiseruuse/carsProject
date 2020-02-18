package com.eliise.cars;

import com.eliise.cars.dao.CarDao;
import com.eliise.cars.dao.CountryDao;
import com.eliise.cars.domain.Car;
import com.eliise.cars.domain.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.UUID;

import static com.eliise.cars.domain.Car.Fuel.BENZINE;
import static com.eliise.cars.domain.Car.Gearbox.AUTOMATIC;
import static com.eliise.cars.domain.Car.Gearbox.MANUAL;

@SpringBootApplication
public class CarsApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CarsApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		List<Car> cars = carDao.selectAllCars();

		/*Car car = new Car();
		car.setCarBrand("BMW");
		car.setCategorySign("M1");
		car.setEnginePower(135);
		car.setColor("DARKGREEN");
		car.setGearbox(MANUAL);
		car.setFuel(BENZINE);

		carDao.insertCar(car);*/

		UUID carUid = UUID.fromString("5aa4d0b8-b5f0-459d-928e-e853e57068f7");
		Car car1 = carDao.selectCarByCarUid(carUid);
		System.out.printf("");

		UUID uuid = UUID.fromString("663ee6a8-6c9a-4cd9-ae61-748af64a210a");
		carDao.deleteCarByCarUid(uuid);

		List<Country> countries = countryDao.selectAllCountries();

		/*Country country = new Country();
		country.setCountryName("ESTONIA");
		country.setLanguage("ESTONIAN");
		country.setIsoCode("EST");
		country.setPopulation(1328000);
		country.setIsEu(true);

		countryDao.insertCountry(country);*/

		UUID countryUid = UUID.fromString("5aa4d0b8-b5f0-459d-928e-e853e57068f7");
		Country country1 = countryDao.selectCountryByCountryUid(countryUid);
		System.out.printf("");

		UUID countryuid = UUID.fromString("663ee6a8-6c9a-4cd9-ae61-748af64a210a");
		countryDao.deleteCountryByCountryUid(countryuid);

	}

	@Autowired
	private CarDao carDao;

	@Autowired
	private CountryDao countryDao;
}
