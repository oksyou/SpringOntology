package ru.oks.diplom.SpringOntology.services;

import ru.oks.diplom.SpringOntology.dto.OntoDto;
import ru.oks.diplom.SpringOntology.dto.RequestDto;

public class OntoService {
    private OntoDto ontoDto;
    private final RequestDto requestDto;

    public OntoService() {
        requestDto = new RequestDto();
    }

    public RequestDto replaceDto(OntoDto ontoDto) {
        this.ontoDto = ontoDto;
        replace();
        return requestDto;
    }

    private int calculatingSquareForOneFloor(int price) {

        int square;
        if (price >= 1400) {
            square = 100 + (price - 1400) / 18;
        } else if (price >= 1000) {
            square = 60 + (price - 1000) / 10;
        } else if (price >= 890) {
            square = 50 + (price - 890) / 11;
        } else square = 40 + (price - 773) / 12;
        return square;
    }

    private int calculatingSquareForTwoFloor(int ontoPrice, int floor) {
        double price = ontoPrice;
        int square = 40;
        double dif = 11.6;
        int minPrice = floor > 2 ? 930 : 838;
        while (price > minPrice) {
            if (dif >= 4.7) {
                square++;
                price -= dif;
                dif -= 0.25;
            } else {
                square++;
                price -= 5.9;
            }
        }
        return square;
    }

    private void replace() {

        requestDto.setNumberFloors(ontoDto.getFloor());
//TODO setNumberBathrooms

        if (ontoDto.getFloor() == 1) {
            requestDto.setMinSquare(calculatingSquareForOneFloor(ontoDto.getMinPrice()));
            requestDto.setMaxSquare(calculatingSquareForOneFloor(ontoDto.getMaxPrice()));
        } else {
            requestDto.setMinSquare(calculatingSquareForTwoFloor(ontoDto.getMinPrice(), ontoDto.getFloor()));
            requestDto.setMaxSquare(calculatingSquareForTwoFloor(ontoDto.getMaxPrice(), ontoDto.getFloor()));
        }
        if (ontoDto.getCar().equals("true")) {
            requestDto.setMinSquare(requestDto.getMinSquare() + 30);
            requestDto.setMaxSquare(requestDto.getMaxSquare() + 30);
            requestDto.setGarage(true);
        }
        if (ontoDto.getComfort().equals("compact")) {
            requestDto.setMinRooms(ontoDto.getPeople() + ontoDto.getCabinet());
            if (ontoDto.getChild().equals("true") & ontoDto.getPeople() > 2) {
                requestDto.setMinRooms(requestDto.getMinRooms() - 1);
            }
            requestDto.setMaxRooms(ontoDto.getPeople() + 1 + ontoDto.getCabinet());
            requestDto.setNumberBathrooms(requestDto.getMaxSquare() / 50 + 1);
        } else if (ontoDto.getComfort().equals("comfort")) {
            requestDto.setMinRooms(ontoDto.getPeople() + ontoDto.getCabinet() - 1);
            requestDto.setMaxRooms(ontoDto.getPeople() + ontoDto.getCabinet() + 1);
            requestDto.setNumberBathrooms(10);
        } else {
            requestDto.setMinRooms(ontoDto.getPeople() + ontoDto.getCabinet() - 1);
            requestDto.setMaxRooms(ontoDto.getPeople() + ontoDto.getCabinet() + 2);
            requestDto.setNumberBathrooms(10);
        }

        if (ontoDto.getChild().equals("true")) {
            requestDto.setRoomsNearby(true);
        }
        if (ontoDto.getRetiree().equals("true")) {
            requestDto.setRoomFirstFloor(true);
        }

        if (ontoDto.getLight().equals("dark")) {
            requestDto.setMaxLight(requestDto.getMaxSquare() / 6);
        }
        if (ontoDto.getLight().equals("light")) {
            requestDto.setMinLight(requestDto.getMinSquare() / 6);
        }
    }
}
