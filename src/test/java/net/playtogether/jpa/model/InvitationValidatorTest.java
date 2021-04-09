package net.playtogether.jpa.model;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import net.playtogether.jpa.entity.Invitation;

import static org.assertj.core.api.Assertions.assertThat;

public class InvitationValidatorTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void shouldNotValidateWhenReceiverNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Invitation invitation = new  Invitation();

		invitation.setName("a");

		Validator validator = createValidator();
		Set<ConstraintViolation<Invitation>> constraintViolations = validator.validate(invitation);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Invitation> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("size must be between 3 and 50");
		
	}

}
