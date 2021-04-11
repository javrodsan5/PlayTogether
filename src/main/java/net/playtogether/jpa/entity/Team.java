package net.playtogether.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

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
    @JoinColumn(name = "users_id")
	private List<Usuario> participants = new ArrayList<>();
    
    private Integer teamSize;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "team_creator")
    private Usuario teamCreator;

    @ManyToOne(optional = true)
    @JoinColumn(name = "owner")
    private Usuario user;
    
    
    
}

