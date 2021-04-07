package net.playtogether.jpa.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import net.playtogether.jpa.entity.Pay;
import net.playtogether.jpa.repository.PayRepository;

@Service
public class PayService {

    private PayRepository payRepository;

    public PayService(PayRepository payRepository) {
        this.payRepository = payRepository;
    }

    @Transactional
    public void save(Pay pay) {
        this.payRepository.save(pay);
    }
    
}
