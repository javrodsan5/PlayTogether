package net.playtogether.jpa.controller;

import java.time.LocalDate;


import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.playtogether.jpa.entity.Championship;

public class ChampionshipValidator implements Validator {

	private static final String REQUIRED = "Campo requerido.";

	@Override
	public void validate(Object target, Errors errors) {
		Championship championship = (Championship) target;
		String city = championship.getCity();
		String description = championship.getDescription();

		LocalDate startDate = championship.getStartDate();
		LocalDate finishDate = championship.getFinishDate();

		if (!StringUtils.hasLength(city)) {
			errors.rejectValue("city", REQUIRED, REQUIRED);
		}

		if (!StringUtils.hasLength(description) || description == null) {
			errors.rejectValue("description", REQUIRED, REQUIRED); 
		}

		if (startDate == null) {
			errors.rejectValue("startDate", REQUIRED, REQUIRED);
		}
		else if (startDate.isBefore(LocalDate.now())) {
			errors.rejectValue("startDate", "La fecha debe ser posterior a la actual.",
					"La fecha debe ser posterior a la actual.");
		}
		
		if (finishDate == null) {
			errors.rejectValue("finishDate", REQUIRED, REQUIRED);
		} else if (finishDate.isBefore(LocalDate.now())) {
			errors.rejectValue("finishDate", "La fecha debe ser posterior a la actual.",
					"La fecha debe ser posterior a la actual.");
		} else if (finishDate.isBefore(startDate)) {
			errors.rejectValue("finishDate", "La fecha debe ser posterior a la de inicio.",
					"La fecha debe ser posterior a la de inicio.");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Championship.class.isAssignableFrom(clazz);
	}

}
