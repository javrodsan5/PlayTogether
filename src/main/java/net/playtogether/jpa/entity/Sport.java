package net.playtogether.jpa.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;


 
@Entity
@Table(name = "sports")
@Setter
@Getter
public class Sport implements Serializable {
 
    private static final long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name = "name")
    private String name;
    
    public Sport() {
    	
    }
    
    public Sport (int id, String name) {
    	this.id=id;
    	this.name=name;
    }



}
