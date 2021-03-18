package net.playtogether.jpa.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


 
@Entity
@Table(name = "payments")
@Setter
@Getter
public class Payment extends BaseEntity {
  
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @NotNull
    @Column(name = "amount")
    private double amount;

    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:SS")
    @Column(name = "date")
    private LocalDate date;

    @ManyToOne(optional=false)
	@JoinColumn(name = "type")
	@NotNull
	private PaymentType paymentType;

    
}
