package net.playtogether.jpa.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.PersistenceConstructor;

import lombok.AccessLevel;
import lombok.Builder.Default;
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
	private List<User> participants = new ArrayList<>();
    
    private Integer teamSize;

    
    
    
}

