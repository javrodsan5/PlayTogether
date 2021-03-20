package net.playtogether.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


 
@Entity
@Table(name = "statistics")
@Setter
@Getter
public class Statistic extends BaseEntity {

    @OneToOne(optional = true)
    private Sport sport;
    
    @OneToOne(optional = true)
    private User user;
}
