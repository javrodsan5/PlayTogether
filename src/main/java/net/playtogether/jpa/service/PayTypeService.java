package net.playtogether.jpa.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import net.playtogether.jpa.entity.PayType;
import net.playtogether.jpa.repository.PayTypeRepository;

@Service
public class PayTypeService {

    private PayTypeRepository payTypeRepository;

    public PayTypeService(PayTypeRepository payTypeRepository) {
        this.payTypeRepository = payTypeRepository;
    }

    @Transactional
    public PayType findById(Integer id) {
        return this.payTypeRepository.findById(id).orElse(null);
    }
    
}
