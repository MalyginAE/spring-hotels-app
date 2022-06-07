package com.ivan.springhotelsapp.entity;

import javax.persistence.*;

@Entity
@Table
public class Visitor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String surname;
    private String room;

    @ManyToOne()
    @JoinColumn(name = "hotel_part_id")
    private HotelPart hotelPart;

    public Visitor() {
    }

    public Visitor(String name, String surname, String room, HotelPart hotelPart) {
        this.name = name;
        this.surname = surname;
        this.room = room;
        this.hotelPart = hotelPart;
    }

    public HotelPart getHotelPart() {
        return hotelPart;
    }

    public void setHotelPart(HotelPart hotelPart) {
        this.hotelPart = hotelPart;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
