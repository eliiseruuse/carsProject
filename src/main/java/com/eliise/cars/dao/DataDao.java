package com.eliise.cars.dao;

import com.eliise.cars.domain.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


public class DataDao  {

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List<Car> selectAllCars() {
        return jdbcTemplate.queryForList("SELECT * FROM CAR", Car.class);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate2;


    public Car selectCarByCarUid(UUID carUid) {
        String sql = "SELECT * FROM CARS WHERE ID = ?" ;
        return (Car) jdbcTemplate2.queryForObject(
                sql,
                new Object[]{carUid},
                new BeanPropertyRowMapper(Car.class)); }
        /*return jdbcTemplate.queryForObject(sql, new Object[]{carUid}, (rs, rowNum) ->
                new Car(
                        rs.getString("carbrand"),
                        rs.getString("categorysign"),
                        rs.getInt("enginepower"),
                        rs.getString("color"),
                        rs.getString("gearbox"),
                        rs.getString("fuel")
                        ));
    }*/

    /*@Override
    public Optional<Car> selectCarByCarUid(UUID carUid) {

        return Optional.empty();
    }*/


    public int deleteCarByCarUid(UUID carUid) {
       /*String sql = "DELETE FROM cars WHERE id = ?";
        Object[] args = new Car[] {carUid};

        return jdbcTemplate.update(sql, args) == 1;
*/
       return 0;
    }


    public int insertCar(UUID carUid, Car car) {
        return 0;

    }
}
