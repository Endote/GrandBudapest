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
public class EmploymentContract {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @ManyToOne
    @JoinColumn(name = "emp_id")
    Person employed;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    Hotel hEmployer;

    @ManyToOne
    @JoinColumn(name = "company_id")
    Company cEmployer;

    @NotNull
    LocalDate startDate;
    LocalDate endDate;

    double salary;

}
