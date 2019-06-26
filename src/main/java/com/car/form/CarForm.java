package com.car.form;

public class CarForm {

    private String id;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CarForm{" +
                "id='" + id + '\'' +
                '}';
    }
}
