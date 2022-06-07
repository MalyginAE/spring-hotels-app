package com.ivan.springhotelsapp.repository;

import com.ivan.springhotelsapp.entity.Visitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VisitorRepository extends JpaRepository<Visitor, Long> {
    @Modifying(clearAutomatically = true)
    @Query("update Visitor v set v.name = :name where v.id = :id")
    void updateName(@Param(value = "id") long id, @Param(value = "name") String name);

    @Modifying(clearAutomatically = true)
    @Query("update Visitor v set v.room = :room where v.id = :id")
    void updateRoom(@Param(value = "id") long id, @Param(value = "room") String room);

    @Modifying(clearAutomatically = true)
    @Query("update Visitor v set v.surname = :surname where v.id = :id")
    void updateSurname(@Param(value = "id") long id, @Param(value = "surname") String surname);

    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM visitor v  WHERE v.hotel_part_id IN (SELECT h.id  FROM hotel_part h WHERE h.hotel_id = :id)",
            nativeQuery = true)
    void deleteAllByHotelId(Long id);


    @Modifying(clearAutomatically = true,flushAutomatically = true)
    @Query(value = "DELETE FROM visitor v  WHERE v.hotel_part_id IN (SELECT  hp.id FROM hotel_part hp LEFT JOIN hotel h ON h.id = hp.hotel_id WHERE h.hotel_name = 'Гостиница Нижний-Новгород');",
            nativeQuery = true)
    void deleteByHotelName(String hotelName);


    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM visitor v  WHERE v.hotel_part_id IN (SELECT hotel_part_id  FROM hotel h WHERE h.hotel_name = :hotelName)" +
            "AND v.hotel_part_id IN (SELECT hotel_part_id  FROM hotel h WHERE h.id = :id)",
            nativeQuery = true)
    void deleteByHotelNameAndId(@Param("hotelName") String name, Long id);


    @Modifying(clearAutomatically = true)
    @Query(value = "DELETE FROM visitor v WHERE" +
            " v.hotel_part_id = :id",
            nativeQuery = true)
    void deleteAllByHotelPartId( Long id);
}