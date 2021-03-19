package net.playtogether.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
 
}