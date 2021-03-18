package net.playtogether.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
    @JoinColumn(name = "championship", referencedColumnName = "id")
    private Championship championship;

    
    @ManyToMany
	@JoinTable(name = "team_users", joinColumns = @JoinColumn(name = "team_id"),
			inverseJoinColumns = @JoinColumn(name = "user_id"))
	private List<User> participants;
    
}
