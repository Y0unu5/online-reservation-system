package com.cg.paymentbooking.repository;

import com.cg.paymentbooking.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    Payment findByBookingId(long id);
}
