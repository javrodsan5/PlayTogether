package net.playtogether.jpa.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


 
@Entity
@Table(name = "championships")
@Setter
@Getter
public class Championship implements Serializable {
 
    private static final long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    
    @Column(name = "sport")
    private Sport sport;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id")
    private List<Team> teamList;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "id")
    private List<Match> match;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:SS")
    @Column(name = "startDate")
    private LocalDateTime startDate;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:SS")
    @Column(name = "finishDate")
    private LocalDateTime finishDate;
}