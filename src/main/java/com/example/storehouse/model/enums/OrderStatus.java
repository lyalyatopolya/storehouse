package com.example.storehouse.model.enums;

public enum OrderStatus {
    CANCELED("Отменён"),
    FORMED("Оформлен");

    private final String name;

    OrderStatus(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
