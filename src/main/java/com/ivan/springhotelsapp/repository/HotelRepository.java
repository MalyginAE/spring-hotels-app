package com.ivan.springhotelsapp.repository;

import com.ivan.springhotelsapp.entity.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel,Long> {
    @Modifying(clearAutomatically = true)
    @Query("update Hotel h set h.hotelName = :hotelName where h.id = :id")
    void updateName(@Param(value = "id") long id, @Param(value = "hotelName") String hotelName);


    List<Hotel> findHotelsByHotelName(String hotelName);



    void deleteByHotelNameAndId(String hotelName, Long id);
}
