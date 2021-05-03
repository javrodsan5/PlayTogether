package net.playtogether.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="chat_type")
@Getter @Setter
public class ChatType extends NamedEntity {

}
