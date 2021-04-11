package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;

import net.playtogether.jpa.entity.PayType;

public interface PayTypeRepository extends CrudRepository<PayType, Integer> {
    
}
