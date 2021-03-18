package net.playtogether.jpa.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;


@Entity
@Table(name = "paymentType")
@Setter
@Getter
public class PaymentType extends NamedEntity {
   

}
