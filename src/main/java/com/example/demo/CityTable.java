package com.example.demo;

public class CityTable {
    private String city_id;
    private String name;

    public CityTable(String city_id, String name) {
        this.city_id = city_id;
        this.name = name;
    }

    public String getCity_id() {
        return city_id;
    }

    public void setCity_id(String city_id) {
        this.city_id = city_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
