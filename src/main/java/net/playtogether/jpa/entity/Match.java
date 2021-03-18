package net.playtogether.jpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


 
@Entity
@Table(name = "matches")
@Setter
@Getter
public class Match implements Serializable {
 
    private static final long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "team1")
    @ManyToOne(optional = false)
    private Team team1;

    @Column(name = "team2")
    @ManyToOne(optional = false)
    private Team team2;

    @NotNull
    @Column(name = "dateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:SS")
    private LocalDateTime dateTime;
    
}
