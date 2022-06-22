package com.hotel.grandbudapest.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Guest extends Person{

    @NotBlank
    String pesel;
    @NotNull
    LocalDate sinceGuest;
    @OneToOne
    Room favouriteRoom;
    @OneToOne
    Service favouriteService;

}
