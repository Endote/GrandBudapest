package com.hotel.grandbudapest.model;


import com.sun.istack.NotNull;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Person {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String phone;
    @NotNull
    private LocalDate since;
    private PersonType type;
    private PersonState state;
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "orderedBy", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Reservation> orderedReservations = new HashSet<>();

    public Set<Reservation> getOrderedReservations() {
        return orderedReservations;
    }

    public boolean changeReservation(Reservation r, LocalDate start, LocalDate end){
        return r.changeDetails(start, end);
    }
}

enum PersonType {
    NORMAL,
    VIP,
    SECRET
}

enum PersonState {
    NEW,
    INCOMING,
    PRESENT,
    PAST,
}

enum Sector {
    KITCHEN,
    GENEROSITY,
    CLEANING,
}
