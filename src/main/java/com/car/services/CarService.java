package com.car.services;

import com.car.entity.Car;
import com.car.form.CarForm;
import com.car.repositories.CarRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarService {

    private final Logger logger = LogManager.getLogger(getClass());

    private CarRepository carRepository;


    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Car locateCar(CarForm carForm) {

        logger.info("Locating car: {}", carForm);

        return carRepository.findById(carForm.getId()).orElseGet(null);
    }

}
