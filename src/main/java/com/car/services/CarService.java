package com.car.services;

import com.car.entity.Car;
import com.car.form.CarForm;
import com.car.repositories.CarRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;

@Service
public class CarService {

    private final Logger logger = LogManager.getLogger(getClass());

    private CarRepository carRepository;


    @Autowired
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public Optional<Car> locateCar(int id) {

        logger.info("Locating car: {}", id);

        return carRepository.findById(id);
    }
    public Car saveCar(CarForm carForm) {
        Car car = new Car(carForm);
        logger.info("Locating car: {}", carForm);

        return carRepository.save(car);
    }

    public void deleteCar(int id) {
        logger.info("Deleting car: {}", id);
        carRepository.deleteById(id);
    }
    public ArrayList showAll(){
        logger.info("Displaying: {}");
        ArrayList<Car> carList = new ArrayList<>();
        Iterable<Car> cars = carRepository.findAll();
        Iterator<Car> carIterator = cars.iterator();
        while(carIterator.hasNext()){
            carList.add(carIterator.next());
        }
        return carList;
    }

}
