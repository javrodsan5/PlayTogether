package net.playtogether.jpa.controller;

import java.time.LocalDateTime;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.playtogether.jpa.entity.Meeting;

public class MeetingValidator implements Validator {

	private static final String REQUIRED = "Campo requerido.";

	@Override
	public void validate(Object target, Errors errors) {
		Meeting meeting = (Meeting) target;
		String city = meeting.getCity();
		String description = meeting.getDescription();
		String address = meeting.getAddress();
		LocalDateTime date = meeting.getDate();

		if (!StringUtils.hasLength(city)) {
			errors.rejectValue("city", REQUIRED, REQUIRED);
		}

		if (!StringUtils.hasLength(address) || address.length() < 3) {
			errors.rejectValue("address", REQUIRED, REQUIRED);
		}

		if (!StringUtils.hasLength(description)) {
			errors.rejectValue("description", REQUIRED, REQUIRED);
		}

		if (date == null) {
			errors.rejectValue("date", REQUIRED, REQUIRED);
		}

		if (date.isBefore(LocalDateTime.now())) {
			errors.rejectValue("date", "La fecha debe ser anterior a la actual.",
					"La fecha debe ser anterior a la actual.");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Meeting.class.isAssignableFrom(clazz);
	}

}
