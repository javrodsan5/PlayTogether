package net.playtogether.jpa.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;


import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Team;

public class MatchValidator implements Validator {

	private static final String REQUIRED = "Campo requerido.";

	@Override
	public void validate(Object target, Errors errors) {
		Match match = (Match) target;
		LocalDateTime dateTime = match.getDateTime();
		Team team1 = match.getTeam1();
		Team team2 = match.getTeam2();
		Championship championship = match.getChampionship();
		LocalDate startDate = championship.getStartDate();
		LocalDate finishDate = championship.getStartDate();

		if (team1 == null) {
			errors.rejectValue("team1", REQUIRED, REQUIRED);
		}

		if (team2 == null) {
				errors.rejectValue("team2", REQUIRED, REQUIRED);

		}
		if (dateTime == null) {
			errors.rejectValue("dateTime", REQUIRED, REQUIRED);
		} 
		
		
		
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Match.class.isAssignableFrom(clazz);
	}

}
