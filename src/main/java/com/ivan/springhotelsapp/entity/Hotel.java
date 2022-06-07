package com.ivan.springhotelsapp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table()
public class Hotel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(nullable = false)
    private String hotelName;

    @OneToMany(mappedBy = "hotel", fetch = FetchType.LAZY)
    private List<HotelPart> hotelParts = new ArrayList<>();

    public Hotel() {
    }

    public Hotel(String hotelName) {
        this.hotelName = hotelName;
    }

    public Hotel(String hotelName, List<HotelPart> hotelParts) {
        this.hotelName = hotelName;
        this.hotelParts = hotelParts;
    }


    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public List<HotelPart> getHotelParts() {
        return hotelParts;
    }

    public void setHotelParts(List<HotelPart> message) {
        this.hotelParts = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
