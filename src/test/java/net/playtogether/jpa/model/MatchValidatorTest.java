package net.playtogether.jpa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;

import static org.assertj.core.api.Assertions.assertThat;

public class MatchValidatorTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
/*
	@Test
	void shouldNotValidateWhenDateNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Championship championship = new  Championship();
		
		Sport sport = new Sport();

		sport.setName("Surf");
		sport.setSportType(new SportType());
		sport.setMeetings(new ArrayList<Meeting>());
		sport.setChampionships(new ArrayList<Championship>());

		championship.setCity("Sevilla");
		championship.setFinishDate(LocalDate.of(2022, 3, 4));
		championship.setStartDate(LocalDate.of(2022, 2, 4));
		championship.setSport(sport);
		championship.setMaxTeams(8);
		championship.setDescription(null);

		Match match = new Match();
		
		match.setChampionship(championship);
	
		Validator validator = createValidator();
		Set<ConstraintViolation<Match>> constraintViolations = validator.validate(match);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Match> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("dateTime");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
		
	}*/
	

	void shouldNotValidateWhenFinishDateNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Championship championship = new  Championship();
		
		Sport sport = new Sport();

		sport.setName("Surf");
		sport.setSportType(new SportType());
		sport.setMeetings(new ArrayList<Meeting>());
		sport.setChampionships(new ArrayList<Championship>());

		championship.setCity("Sevilla");
		championship.setFinishDate(null);
		championship.setStartDate(LocalDate.of(2022, 2, 4));
		championship.setSport(sport);
		championship.setMaxTeams(8);
		championship.setDescription("hola es una prueba");

	
		Validator validator = createValidator();
		Set<ConstraintViolation<Championship>> constraintViolations = validator.validate(championship);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Championship> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("finishDate");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
		
	}
	

}
