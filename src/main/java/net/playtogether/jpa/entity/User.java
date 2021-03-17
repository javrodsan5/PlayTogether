package net.playtogether.jpa.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;
import lombok.Setter;


 
@Entity
@Table(name = "users")
@Setter
@Getter
public class User implements Serializable {
 
    private static final long serialVersionUID = 1L;
     
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
     
    @Email
    @Column(name = "correo")
    private String correo;

    @NotNull
    @Column(name = "username")
    private String username;

    @NotNull
    @Column(name = "name")
    private String name;
    
    @NotNull
    @Column(name = "surname")
    private String surname;

    @NotNull
    @Column(name = "password")
    private String password;
    
    @NotNull
    @Column(name = "birthdate")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate birthdate;
    
    @NotNull
    @Column(name = "phone")
    @Pattern(regexp = "[0-9]{9}")
    private String phone;

    @ManyToOne(optional = true)
    @Column(name = "payment")
    private List<String> payment;
    
    @OneToOne(optional = true)
    @Column(name = "statistics")
    private Statistic statistics;
    
    @OneToOne(optional = false)
    @Column(name = "type")
    private UserType type;

 
}
