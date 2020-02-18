package com.eliise.cars.domain;

import java.util.UUID;

public class Car {

    private  UUID carUid;

    private  String carBrand;

    private  String categorySign;

    private  int enginePower;

    private  String color;

    private  Gearbox gearbox;

    public Car() {
    }

    public Car(UUID carUid, String carBrand, String categorySign, int enginePower, String color, Gearbox gearbox, Fuel fuel) {
        this.carUid = carUid;
        this.carBrand = carBrand;
        this.categorySign = categorySign;
        this.enginePower = enginePower;
        this.color = color;
        this.gearbox = gearbox;
        this.fuel = fuel;
    }

    public UUID getCarUid() {
        return carUid;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getCategorySign() {
        return categorySign;
    }

    public int getEnginePower() {
        return enginePower;
    }

    public String getColor() {
        return color;
    }

    public Gearbox getGearbox() {
        return gearbox;
    }

    public Fuel getFuel() {
        return fuel;
    }

    private  Fuel fuel;

    public void setCarUid(UUID carUid) {
        this.carUid = carUid;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public void setCategorySign(String categorySign) {
        this.categorySign = categorySign;
    }

    public void setEnginePower(int enginePower) {
        this.enginePower = enginePower;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setGearbox(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public static Car newCar(UUID carUid, Car car) {
        return new Car(carUid, car.getCarBrand(),
                car.getCategorySign(), car.getEnginePower(),
                car.getColor(), car.getGearbox(), car.getFuel());
    }



    @Override
    public String toString() {
        return "Car{" +
                "carUid=" + carUid +
                ", carBrand='" + carBrand + '\'' +
                ", categorySign='" + categorySign + '\'' +
                ", enginePower=" + enginePower +
                ", color='" + color + '\'' +
                ", gearbox=" + gearbox +
                ", fuel=" + fuel +
                '}';
    }

    public enum Fuel {
        BENZINE,
        DIESEL
    }

    public enum Gearbox {
        AUTOMATIC,
        MANUAL
    }


}
