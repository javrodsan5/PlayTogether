package net.playtogether.jpa.model;

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
import static org.assertj.core.api.Assertions.assertThat;

public class SportValidatorTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void shouldNotValidateWhenSportTypeNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Sport sport = new Sport();

		sport.setName("Surf");
		sport.setSportType(null);
		sport.setMeetings(new ArrayList<Meeting>());
		sport.setChampionships(new ArrayList<Championship>());
	
		Validator validator = createValidator();
		Set<ConstraintViolation<Sport>> constraintViolations = validator.validate(sport);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Sport> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("sportType");
		assertThat(violation.getMessage()).isEqualTo("must not be null");
		
	}

}
