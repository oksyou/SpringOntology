package ru.oks.diplom.SpringOntology.dto;

public class OntoDto {
    private int people;
    private String child;
    private String retiree;
    private String comfort;
    private int minPrice;
    private int maxPrice;
    private int floor;
    private String city;
    private String light;
    private int cabinet;
    private String car;

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }

    public int getPeople() {
        return people;
    }

    public void setPeople(int people) {
        this.people = people;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getRetiree() {
        return retiree;
    }

    public void setRetiree(String retiree) {
        this.retiree = retiree;
    }

    public int getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMaxPrice() {
        return maxPrice;
    }

    public void setMaxPrice(int maxPrice) {
        this.maxPrice = maxPrice;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCabinet() {
        return cabinet;
    }

    public void setCabinet(int cabinet) {
        this.cabinet = cabinet;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }
}
