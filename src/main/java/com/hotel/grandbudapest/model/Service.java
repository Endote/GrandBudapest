package com.hotel.grandbudapest.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    LocalDate time;
    @OneToOne
    Manager deployedBy;
    @OneToOne
    Employee executedBy;
    @OneToOne
    Person executedFor;


    boolean isPaid;

}

enum ServiceType{}
