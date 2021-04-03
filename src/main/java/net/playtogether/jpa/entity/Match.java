
package net.playtogether.jpa.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
public class Match extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "championships_id")
	private Championship	championship;

	@ManyToOne(optional = false)
	private Team			team1;

	@ManyToOne(optional = false)
	private Team			team2;

	@NotNull
	@Column(name = "dateTime")
	@DateTimeFormat(pattern = "yyyy/MM/dd HH:mm")
	private LocalDateTime	dateTime;

	@Column(name = "puntos1")
	private Integer			puntos1;

	@Column(name = "puntos2")
	private Integer			puntos2;

	@Column(name = "puntos3")
	private Integer			puntos3;

	@Column(name = "puntos4")
	private Integer			puntos4;

	
}
