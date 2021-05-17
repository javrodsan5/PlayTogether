package net.playtogether.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="meeting_category")
@Getter @Setter
public class MeetingCategory extends NamedEntity {
    
}
