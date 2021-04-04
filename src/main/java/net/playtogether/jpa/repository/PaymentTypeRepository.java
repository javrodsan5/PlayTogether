package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.PaymentType;

@Repository
public interface PaymentTypeRepository extends CrudRepository<PaymentType, Integer> {
 
}
