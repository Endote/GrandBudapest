package com.hotel.grandbudapest.model;

import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

public class Employee extends Person implements IWorking{

    @NotNull
    LocalDate sinceEmployed;
    Sector sector;

    @ManyToOne
    Manager manager;

    @ManyToOne
    Hotel works;

    @OneToMany(mappedBy = "takeIn", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Reservation> reservation = new HashSet<>();
}


