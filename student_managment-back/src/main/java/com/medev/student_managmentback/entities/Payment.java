package com.medev.student_managmentback.entities;


import com.medev.student_managmentback.enums.PaymentStatus;
import com.medev.student_managmentback.enums.PaymentType;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Builder @ToString
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate date;
    private double amount;
    private PaymentType type;
    private PaymentStatus status;
    private String file;

    @ManyToOne
    private Student student;
}
