package net.playtogether.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


 
@Entity
@Table(name = "sports")
@Setter
@Getter
public class Sport extends NamedEntity {

    @ManyToOne(optional=false)
	@JoinColumn(name = "sportType")
	@NotNull
    private SportType sportType;
    
}
