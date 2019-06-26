package com.car.controller;

import com.car.entity.Car;
import com.car.form.CarForm;
import com.car.services.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

    private final Logger logger = LogManager.getLogger(getClass());

    private final CarService carService;


    @Autowired
    public ApiController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping(value = "/car", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> hello(@RequestBody CarForm carForm) {

        logger.info("New  Request {}", carForm);

        Car car = carService.locateCar(carForm);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }
}
