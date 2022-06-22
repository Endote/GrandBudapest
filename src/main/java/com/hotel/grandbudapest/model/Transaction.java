package com.hotel.grandbudapest.model;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotBlank
    String name;
    @NotNull
    LocalDate time;

    @ManyToOne
    Employee supervisedby;

    @OneToOne
    Person paidBy;

    boolean result;

    TransactionType type;

    @OneToOne
    Service forr;
}

enum TransactionType{}
