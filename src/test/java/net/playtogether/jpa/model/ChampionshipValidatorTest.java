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
import net.playtogether.jpa.entity.Usuario;

import static org.assertj.core.api.Assertions.assertThat;

public class ChampionshipValidatorTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void shouldNotValidateWhenDescriptionNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Championship championship = new  Championship();
		
		Sport sport = new Sport();

		sport.setName("Surf");
		sport.setSportType(new SportType());
		sport.setMeetings(new ArrayList<Meeting>());
		sport.setChampionships(new ArrayList<Championship>());

		championship.setName("Torneo");
		championship.setCity("Sevilla");
		championship.setAddress("Polideportivo Ciudad Jardin");
		championship.setFinishDate(LocalDate.of(2022, 3, 4));
		championship.setStartDate(LocalDate.of(2022, 2, 4));
		championship.setSport(sport);
		championship.setMaxTeams(8);
		championship.setDescription(null);
		championship.setUser(new Usuario());

	
		Validator validator = createValidator();
		Set<ConstraintViolation<Championship>> constraintViolations = validator.validate(championship);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Championship> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("description");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
		
	}
	
	@Test
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
		championship.setAddress("Polideportivo Ciudad Jardin");
		championship.setStartDate(LocalDate.of(2022, 2, 4));
		championship.setSport(sport);
		championship.setMaxTeams(8);
		championship.setDescription("hola es una prueba");
		championship.setUser(new Usuario());

	
		Validator validator = createValidator();
		Set<ConstraintViolation<Championship>> constraintViolations = validator.validate(championship);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Championship> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("finishDate");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
		
	}
	
	@Test
	void shouldNotValidateWhenCityNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Championship championship = new  Championship();
		
		Sport sport = new Sport();

		sport.setName("Surf");
		sport.setSportType(new SportType());
		sport.setMeetings(new ArrayList<Meeting>());
		sport.setChampionships(new ArrayList<Championship>());

		championship.setCity(null);
		championship.setAddress("Polideportivo Ciudad Jardin");
		championship.setFinishDate(LocalDate.of(2022, 3, 4));
		championship.setStartDate(LocalDate.of(2022, 2, 4));
		championship.setSport(sport);
		championship.setMaxTeams(8);
		championship.setDescription("hola es una prueba");
		championship.setUser(new Usuario());

	
		Validator validator = createValidator();
		Set<ConstraintViolation<Championship>> constraintViolations = validator.validate(championship);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Championship> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("city");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
		
	}
	
	@Test
	void shouldNotValidateWhenStartDateNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Championship championship = new  Championship();
		
		Sport sport = new Sport();

		sport.setName("Surf");
		sport.setSportType(new SportType());
		sport.setMeetings(new ArrayList<Meeting>());
		sport.setChampionships(new ArrayList<Championship>());

		championship.setCity("Sevilla");
		championship.setAddress("Polideportivo Ciudad Jardin");
		championship.setFinishDate(LocalDate.of(2022, 3, 4));
		championship.setStartDate(null);
		championship.setSport(sport);
		championship.setMaxTeams(8);
		championship.setDescription("hola es una prueba");
		championship.setUser(new Usuario());

	
		Validator validator = createValidator();
		Set<ConstraintViolation<Championship>> constraintViolations = validator.validate(championship);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Championship> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("startDate");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
		
	}
	
	@Test
	void shouldNotValidateWhenAddressNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Championship championship = new  Championship();
		
		Sport sport = new Sport();

		sport.setName("Surf");
		sport.setSportType(new SportType());
		sport.setMeetings(new ArrayList<Meeting>());
		sport.setChampionships(new ArrayList<Championship>());

		championship.setCity("Sevilla");
		championship.setAddress(null);
		championship.setFinishDate(LocalDate.of(2022, 3, 4));
		championship.setStartDate(LocalDate.of(2022, 2, 4));
		championship.setSport(sport);
		championship.setMaxTeams(8);
		championship.setDescription("hola es una prueba");
		championship.setUser(new Usuario());

	
		Validator validator = createValidator();
		Set<ConstraintViolation<Championship>> constraintViolations = validator.validate(championship);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Championship> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("address");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
		
	}

}
