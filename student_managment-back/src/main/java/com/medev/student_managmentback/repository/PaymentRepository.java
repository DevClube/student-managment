package com.medev.student_managmentback.repository;

import com.medev.student_managmentback.entities.Payment;
import com.medev.student_managmentback.enums.PaymentStatus;
import com.medev.student_managmentback.enums.PaymentType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    List<Payment> findByStudentCode(String code);
    List<Payment> findByStatus(PaymentStatus Status);
    List<Payment> findByType(PaymentType type);
}
