package net.playtogether.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


 
@Entity
@Table(name = "invitations")
@Setter
@Getter
public class Invitation extends NamedEntity {

    @ManyToOne	
    @JoinColumn(name = "receiver_id")
	private Usuario receiver;
    
    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;

    @ManyToOne
    @JoinColumn(name = "meeting_id")
    private Meeting meeting;
    
}

