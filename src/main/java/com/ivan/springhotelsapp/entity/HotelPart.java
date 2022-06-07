package com.ivan.springhotelsapp.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class HotelPart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    private String hotelPartAddress;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;


    @OneToMany(mappedBy = "hotelPart", fetch = FetchType.LAZY)
    List<Visitor> visitors = new ArrayList<>();

    public List<Visitor> getVisitors() {
        return visitors;
    }



    public void setVisitors(List<Visitor> visitors) {
        this.visitors = visitors;
    }

    public HotelPart(String hotelPartAddress, Hotel hotel) {
        this.hotelPartAddress = hotelPartAddress;
        this.hotel = hotel;
    }

    public HotelPart() {
    }

    public String getHotelPartAddress() {
        return hotelPartAddress;
    }

    public void setHotelPartAddress(String hotelPartAddress) {
        this.hotelPartAddress = hotelPartAddress;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
