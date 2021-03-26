package net.playtogether.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import lombok.Getter;
import lombok.Setter;


 
@Entity
@Table(name = "teams")
@Setter
@Getter
public class Team extends NamedEntity {

    @ManyToOne
    @JoinColumn(name = "championships_id")
    private Championship championship;
    
    @ManyToMany
	private List<User> participants;
    
    
}
