package ru.oks.diplom.SpringOntology.dto;

public class ResultDto {
    private String house;
    private int rooms;
    private int bathrooms;
    private int square;

    public String getHouse() {
        return house;
    }

    public void setHouse(int house) {
        this.house = "pictures/z" +house+".png";
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getBathrooms() {
        return bathrooms;
    }

    public void setBathrooms(int bathrooms) {
        this.bathrooms = bathrooms;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
    }
}
