package com.hotel.grandbudapest.repository;


import lombok.RequiredArgsConstructor;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;



@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final ReservationRepository reservationRepository;

    @EventListener
    public void atStart(ContextRefreshedEvent ev) {
        System.out.println("Context has been refreshed!");
        System.out.println(reservationRepository.findAll());
    }

}
