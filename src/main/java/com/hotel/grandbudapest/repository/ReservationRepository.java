package com.hotel.grandbudapest.repository;

import com.hotel.grandbudapest.model.Reservation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends CrudRepository<Reservation, Long> {
//    public List<Reservation> findByPerson(Person p);

    @Query("from Reservation  where orderedBy = :currentPerson")
    public List<Reservation> findByPersonID(@Param("currentPerson") Long id);
}
