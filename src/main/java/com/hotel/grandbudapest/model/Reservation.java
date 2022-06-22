package com.hotel.grandbudapest.model;

import com.hotel.grandbudapest.repository.ReservationRepository;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "ordered_by")
    Person orderedBy;

    @ManyToOne
    @JoinColumn(name = "taken_by")
    Employee takeIn;

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    Hotel reservation;

    @OneToOne
    Room room;

    @NotNull
    private LocalDate startDate;
    private LocalDate endDate;

    private double totalPrice;


    public boolean changeDetails(LocalDate start, LocalDate end){


        if(end.isBefore(start)) return false;

        Hotel tmp = this.getRoom().getOwner();

        this.endDate = end;
        this.startDate = start;

        return true;
    }
}
