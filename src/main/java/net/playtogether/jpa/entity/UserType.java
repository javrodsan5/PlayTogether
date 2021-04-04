package net.playtogether.jpa.entity;


import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "userType")
@Setter
@Getter
public class UserType extends NamedEntity {

}
