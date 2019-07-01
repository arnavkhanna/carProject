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
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class ApiController {

    private final Logger logger = LogManager.getLogger(getClass());

    private final CarService carService;


    @Autowired
    public ApiController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping(value = "/car/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> get(@PathVariable int id) {

        logger.info("New  Request {}", id);

        Optional<Car> carVar = carService.locateCar(id);
        if (carVar.isPresent()){
            return new ResponseEntity<>(carVar.get(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Car not found by ID.", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "/car", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Car> put(@RequestBody CarForm carForm) {

        logger.info("New  Request {}", carForm);

        Car car = carService.saveCar(carForm);

        return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @DeleteMapping(value = "/car/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> delete(@PathVariable int id) {

        logger.info("New  Request {}", id);

        Optional<Car> carVar = carService.locateCar(id);
        if (carVar.isPresent()){
            carService.deleteCar(id);
            return new ResponseEntity<>("Car deleted.", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Car not found by ID.", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping (value = "/car/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> show(){
        logger.info("New Request {}");
        return new ResponseEntity<>(carService.showAll().toString(), HttpStatus.OK);

    }


}
