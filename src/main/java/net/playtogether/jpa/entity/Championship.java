package net.playtogether.jpa.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
public class Championship extends NamedEntity {
 
    @ManyToOne(optional = false)
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @OneToMany(mappedBy = "championship")
    private List<Team> teams;

    @NotNull
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "city")
    private String city;

    @OneToMany(mappedBy = "championship")
    private List<Match> matches;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:SS")
    @Column(name = "startDate")
    private LocalDateTime startDate;
    
    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:SS")
    @Column(name = "finishDate")
    private LocalDateTime finishDate;
}
