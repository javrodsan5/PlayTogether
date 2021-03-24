package net.playtogether.jpa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;

import static org.assertj.core.api.Assertions.assertThat;

public class MeetingValidatorTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void shouldNotValidateWhenNameIsTooShort() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Meeting meeting = new Meeting();

		meeting.setId(1);
		meeting.setName("Q1");
		meeting.setCity("Sevilla");
		meeting.setDescription("Quedada para jugar al futbol");
		meeting.setAddress("Calle Antonio Ulloa");
		LocalDate fecha = LocalDate.of(2021, 6, 15);
		LocalTime hora = LocalTime.of(18, 16);
		LocalDateTime fechaHora = LocalDateTime.of(fecha, hora);
		meeting.setDate(fechaHora);
		meeting.setParticipants(null);
		meeting.setSport(null);
	
		Validator validator = createValidator();
		Set<ConstraintViolation<Meeting>> constraintViolations = validator.validate(meeting);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Meeting> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("size must be between 3 and 50");
		
	}

}
