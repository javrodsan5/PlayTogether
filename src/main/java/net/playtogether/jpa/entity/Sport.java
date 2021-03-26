package net.playtogether.jpa.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;

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

    @OneToMany(mappedBy = "sport")
    private List<Championship> championships;

    @OneToMany(mappedBy = "sport")
    private List<Meeting> meetings;
    
    @Value("1")
    private Integer numberOfPlayersInTeam;
    
}
