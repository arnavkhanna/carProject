package com.car.controller;

import com.car.entity.Car;
import com.car.form.CarForm;
import com.car.services.CarService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Optional;


@Controller
public class WebController {

    private final Logger logger = LogManager.getLogger(getClass());
    private final CarService carService;

    public WebController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/")
    public String index(Model model) {

        logger.info("request to index page");

        model.addAttribute("carform", new CarForm());
        return "getPage";
    }

    @PostMapping(value = "/")
    public String post(@ModelAttribute CarForm carForm, Model model){
        logger.info(carForm);
        Optional<Car> carVar = carService.locateCar(carForm.getId());
        if (carVar.isPresent()) {
            Car car = carVar.get();
            model.addAttribute("car", car);
            return "getPageResults";
        }
        else{
            model.addAttribute("carform", new CarForm());
            model.addAttribute("Error", "ID not found");
            return "getPage";
        }




    }

    @GetMapping(value = "/add")
    public String add(@ModelAttribute CarForm carForm, Model model){
        logger.info(carForm);
        model.addAttribute("buttonVal", "Add");
        model.addAttribute("carform", new CarForm());
        model.addAttribute("Action","add");
        return "addPage";

    }

    @PostMapping(value = "/add")
    public String addResults(@ModelAttribute CarForm carForm, Model model){
        model.addAttribute("Action","add");
        logger.info(carForm);
        Car car = carService.saveCar(carForm);
        model.addAttribute("buttonVal", "Add");
        if (car == null){
            model.addAttribute("carform", carForm);
            model.addAttribute("Message", "1");
            return "addPage";
        }
        else{
            model.addAttribute("carform",new CarForm());
            model.addAttribute("Message", "2");
            return "addPage";
        }
    }

    @GetMapping(value = "/all")
    public String listAll(Model model){
        ArrayList<Car> carList = new ArrayList<>();
        carList = carService.showAll();
        model.addAttribute("cars", carList);
        model.addAttribute("Message", "3");
        return "listPage";
    }
    @GetMapping(value = "/edit/{id}")
    public String edit(Model model, @PathVariable int id){
        model.addAttribute("Action","/edit/" + id);
        Optional<Car> carVar = carService.locateCar(id);
        if (carVar.isPresent()){
            CarForm carForm = new CarForm(carVar.get());
            model.addAttribute("carform", carForm);
        }
        else{
            model.addAttribute("Message","3");
        }

        model.addAttribute("buttonVal", "Update");
        return "addPage";
    }
    @PostMapping(value = "/edit/{id}")
    public String editPost(Model model, CarForm carForm, @PathVariable int id){
        model.addAttribute("Action","/edit/" + id);
        model.addAttribute("Message","4");
        model.addAttribute("buttonVal", "Update");
        model.addAttribute("carform", carForm);
        carService.saveCar(carForm);
        return "addPage";
    }

    @GetMapping(value = "/remove/{id}")
    public String delete(Model model, @PathVariable int id){
        Optional<Car> carVar = carService.locateCar(id);
        if (carVar.isPresent()){
            carService.deleteCar(id);
            model.addAttribute("Message","2");
            ArrayList<Car> carList = new ArrayList<>();
            carList = carService.showAll();
            model.addAttribute("cars", carList);
            return "listPage";
        }
        else {
            model.addAttribute("Message","1");
            return "listPage";
        }
    }

}
