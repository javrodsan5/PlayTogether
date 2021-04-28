package net.playtogether.jpa.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


 
@Entity
@Table(name = "pay")
@Setter
@Getter
public class Pay extends BaseEntity {
  
    @ManyToOne
    @JoinColumn(name = "user_id")
    private Usuario user;

    @Column(name = "amount")
    private double amount;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:SS")
    @Column(name = "date")
    private LocalDate date;

    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:SS")
    @Column(name = "init_date")
    private LocalDateTime initDate;

    @ManyToOne(optional = true)
    @JoinColumn(name = "championship_id")
    private Championship championship;

    @ManyToOne(optional=true)
    @JoinColumn(name = "pay_type_id")
    private PayType payType;

    @ManyToOne(optional=true)
    @JoinColumn(name = "team_id")
    private Team team;

    @Column(name = "invitation_id")
    private Integer invitationId;

    
}
