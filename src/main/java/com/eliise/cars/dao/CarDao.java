package com.eliise.cars.dao;

import com.eliise.cars.domain.Car;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;
import java.util.UUID;

@Repository
public class CarDao {

    private final NamedParameterJdbcOperations jdbcOperations;

    public CarDao(DataSource dataSource) {
        this.jdbcOperations = new NamedParameterJdbcTemplate(dataSource);
    }

    public List<Car> selectAllCars() {
        BeanPropertyRowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcOperations.query("select * from public.cars", rowMapper);
    }

    public Car selectCarByCarUid(UUID carUid) {
        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("carUid", carUid);

        BeanPropertyRowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        List<Car> cars = jdbcOperations.query("select * from public.cars where car_uid = :carUid", parameterSource, rowMapper);

        if (cars.isEmpty()) {
            return null;
        } else {
            return cars.get(0);
        }
    }

    public int deleteCarByCarUid(UUID carUid) {

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("carUid", carUid);

        jdbcOperations.update("delete from public.cars where car_uid = :carUid", parameterSource);

        return 0;
    }

    public UUID insertCar(Car car) {
        UUID uuid = UUID.randomUUID();

        MapSqlParameterSource parameterSource = new MapSqlParameterSource();
        parameterSource.addValue("carUid", uuid);
        parameterSource.addValue("carBrand", car.getCarBrand());
        parameterSource.addValue("categorySign", car.getCategorySign());
        parameterSource.addValue("enginePower", car.getEnginePower());
        parameterSource.addValue("color", car.getColor());
        parameterSource.addValue("gearbox", car.getGearbox().toString());
        parameterSource.addValue("fuel", car.getFuel().toString());

        jdbcOperations.update("insert into public.cars (car_uid, car_brand, category_sign, engine_power, color, gearbox, fuel) " +
                "values (:carUid, :carBrand, :categorySign, :enginePower, :color, :gearbox, :fuel)", parameterSource);

        return uuid;


    }

}
