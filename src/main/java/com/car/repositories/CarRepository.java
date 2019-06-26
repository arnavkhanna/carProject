package com.car.repositories;

import com.car.entity.Car;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CarRepository extends PagingAndSortingRepository<Car, String> {
}
