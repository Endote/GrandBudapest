package com.hotel.grandbudapest.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Builder
public class Manager extends Person implements IWorking{

    LocalDate sinceManager;
    Sector sector;
    @OneToMany(mappedBy = "manager", fetch = FetchType.LAZY)
    @Builder.Default
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    Set<Employee> team  = new HashSet<>();
}
