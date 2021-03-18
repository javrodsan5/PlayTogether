package net.playtogether.jpa.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


 
@Entity
@Table(name = "meetings")
@Setter
@Getter
public class Meeting extends NamedEntity {
 
    @NotNull
    @Column(name = "address")
    private String address;

    @NotNull
    @Column(name = "city")
    private String city;

    @NotNull
    @DateTimeFormat(pattern = "yyyy/MM/dd HH:mm:SS")
    @Column(name = "date")
    private LocalDate date;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "id")
    private List<User> participants;

    @NotNull
    @Column(name = "description")
    private String description;

    @ManyToOne(optional = false)
    private Sport sport;

}
