package com.hotel.grandbudapest.repository;

import com.hotel.grandbudapest.model.Hotel;
import com.hotel.grandbudapest.model.Room;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.Set;


public interface RoomRepository extends CrudRepository<Room, Long> {

    @Query("from Room  where owner = :owner")
    public Set<Room> findByHotel(@Param("owner") Hotel hotel);

    @Query("from Room where roomNumber = :number")
    public Set<Room> findByRoomNumber(@Param("number") int number);
}
