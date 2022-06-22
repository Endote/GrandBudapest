package com.hotel.grandbudapest.controller;

import com.hotel.grandbudapest.App;
import com.hotel.grandbudapest.model.Reservation;
import com.hotel.grandbudapest.model.Room;
import com.hotel.grandbudapest.repository.ReservationRepository;
import com.hotel.grandbudapest.repository.RoomRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Controller
public class UseCaseController {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    RoomRepository roomRepository;

    App app;

    public List<Reservation> getReservations(Long PersonID) {
        List<Reservation> reservations = new ArrayList<>();
        reservationRepository.findAll().forEach(reservations::add);
        return reservations;
    }


    public void startApplication() throws InstantiationException, IllegalAccessException {
        app = new App(this);
    }

    public boolean changeDetails(Reservation r, LocalDate start, LocalDate end, int roomNumber){
        if(end.isBefore(start)) return false;

        boolean isRoom = false;

        Set<Room> roomSet = new HashSet<>();
        roomRepository.findByHotel(r.getRoom().getOwner()).forEach(roomSet::add);

        Iterable<Reservation> tmpR = reservationRepository.findAll();

        // Check if room is existing in the correct Hotel
        for(Room room : roomSet){
            if(room.getRoomNumber() == roomNumber){
                isRoom = true;
                break;
            }
        }
        if(!isRoom) return false;

        // Get room object from our form if it exists
        Room exact = (Room) roomRepository.findByRoomNumber(roomNumber).toArray()[0];

        // Validation if reservation don't overlap on the same room and date
        for(Reservation rr : tmpR){
            if(!r.equals(rr) && exact.equals(rr.getRoom())) {
                if ((start.isBefore(rr.getStartDate()) && (end.isAfter(rr.getStartDate()) && end.isBefore(rr.getEndDate()))
                        || (start.isBefore(rr.getEndDate()) && end.isAfter(rr.getEndDate())))) {
                    return false;
                }
            }
        }

        r.setRoom(exact);
        r.setEndDate(end);
        r.setStartDate(start);
        reservationRepository.save(r);

        return true;
    }

}
