package ru.oks.diplom.SpringOntology.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "project")
public class Project {
    @Id
    @Column(name = "doc_name")
    private String docName;
    @Column(name="square")
    private int square;
    @Column(name="number_rooms")
    private int rooms;
    @Column(name="number_bathrooms")
    private int bathrooms;
    @Column(name = "light")
    private String light;

    public Project(String docName) {
        this.docName = docName;
    }

    public Project() {
    }

    public Project(String docName, int square, int rooms, int bathrooms) {
        this.docName = docName;
        this.square = square;
        this.rooms = rooms;
        this.bathrooms = bathrooms;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public int getSquare() {
        return square;
    }

    public void setSquare(int square) {
        this.square = square;
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

    public String getLight() {
        return light;
    }

    public void setLight(String light) {
        this.light = light;
    }
}
