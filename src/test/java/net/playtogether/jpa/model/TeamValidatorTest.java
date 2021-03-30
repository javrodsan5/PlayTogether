package net.playtogether.jpa.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;
import net.playtogether.jpa.entity.Team;

import static org.assertj.core.api.Assertions.assertThat;

public class TeamValidatorTest {
	
	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}
	
	@Test
	void shouldNotValidateWhenNameIsLessThanThree() {
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
		championship.setDescription("hola es una prueba");
		
		Team team = new Team();
		team.setName("aa");

	
		Validator validator = createValidator();
		Set<ConstraintViolation<Team>> constraintViolations = validator.validate(team);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Team> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("size must be between 3 and 50");
		
	}
	
	@Test
	void shouldNotValidateWhenNameIsGreaterThanThree() {
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
		championship.setDescription("hola es una prueba");
		
		Team team = new Team();
		team.setName("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");

	
		Validator validator = createValidator();
		Set<ConstraintViolation<Team>> constraintViolations = validator.validate(team);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Team> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("size must be between 3 and 50");
		
	}

}
