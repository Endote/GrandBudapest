package com.hotel.grandbudapest.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Keycard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @OneToOne
    Room room;

    @OneToOne
    Employee deployedBy;

    @OneToOne
    Person deployedFor;

    @OneToMany(mappedBy = "accepted", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Hotel> accepted = new HashSet<>();

}
