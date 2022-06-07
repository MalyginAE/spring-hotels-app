package com.ivan.springhotelsapp.repository;

import com.ivan.springhotelsapp.entity.Hotel;
import com.ivan.springhotelsapp.entity.HotelPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface HotelPartRepository extends JpaRepository<HotelPart,Long> {
    void deleteHotelPartByHotelPartAddress(String hotelPartAddress);

    @Modifying(clearAutomatically = true)
    @Query("update HotelPart h set h.hotelPartAddress = :address where h.id = :id")
    void updateAddress(@Param(value = "id") Long id, @Param(value = "address") String address);

    void deleteAllByHotelId(Long hotelId);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM hotel_part h  WHERE h.hotel_id IN (SELECT hotel_id  FROM hotel h WHERE" +
            " h.hotel_name = :hotelName)",nativeQuery = true)
    void deleteByHotelName(String hotelName);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM hotel_part h  WHERE h.hotel_id IN (SELECT hotel_id  FROM hotel h WHERE h.hotel_name = :hotelName) AND " +
            "h.hotel_id IN (SELECT hotel_id FROM hotel h WHERE h.id = :id)",nativeQuery = true)
    void deleteByHotelNameAndId(@Param("hotelName") String name, Long id);
}
