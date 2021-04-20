package ru.oks.diplom.SpringOntology.dto;

public class RequestDto {
    private int minSquare;
    private int maxSquare;
    private int numberBathrooms;
    private int minRooms;
    private int maxRooms;
    private int numberFloors;
    private boolean roomsNearby;
    private boolean roomFirstFloor;
    private boolean garage;
    private int minLight;
    private int maxLight;
    private boolean terrace;

    public boolean isTerrace() {
        return terrace;
    }

    public void setTerrace(boolean terrace) {
        this.terrace = terrace;
    }

    public int getMaxSquare() {
        return maxSquare;
    }

    public void setMaxSquare(int maxSquare) {
        this.maxSquare = maxSquare;
    }

    public int getMinSquare() {
        return minSquare;
    }

    public void setMinSquare(int minSquare) {
        this.minSquare = minSquare;
    }

    public int getNumberBathrooms() {
        return numberBathrooms;
    }

    public void setNumberBathrooms(int numberBathrooms) {
        this.numberBathrooms = numberBathrooms;
    }

    public int getMinRooms() {
        return minRooms;
    }

    public void setMinRooms(int minRooms) {
        this.minRooms = minRooms;
    }

    public int getMaxRooms() {
        return maxRooms;
    }

    public void setMaxRooms(int maxRooms) {
        this.maxRooms = maxRooms;
    }

    public int getNumberFloors() {
        return numberFloors;
    }

    public void setNumberFloors(int numberFloors) {
        this.numberFloors = numberFloors;
    }

    public boolean isRoomsNearby() {
        return roomsNearby;
    }

    public void setRoomsNearby(boolean roomsNearby) {
        this.roomsNearby = roomsNearby;
    }

    public boolean isRoomFirstFloor() {
        return roomFirstFloor;
    }

    public void setRoomFirstFloor(boolean roomFirstFloor) {
        this.roomFirstFloor = roomFirstFloor;
    }

    public boolean isGarage() {
        return garage;
    }

    public void setGarage(boolean garage) {
        this.garage = garage;
    }

    public int getMinLight() {
        return minLight;
    }

    public void setMinLight(int minLight) {
        this.minLight = minLight;
    }

    public int getMaxLight() {
        return maxLight;
    }

    public void setMaxLight(int maxLight) {
        this.maxLight = maxLight;
    }
}
