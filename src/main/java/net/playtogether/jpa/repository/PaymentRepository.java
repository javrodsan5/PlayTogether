package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Payment;

@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer> {
 
}
