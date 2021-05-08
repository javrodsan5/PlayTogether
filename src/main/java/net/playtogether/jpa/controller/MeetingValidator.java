package net.playtogether.jpa.controller;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.MeetingCategory;

public class MeetingValidator implements Validator {

	private static final String REQUIRED = "Campo requerido.";

	@Override
	public void validate(Object target, Errors errors) {
		Meeting meeting = (Meeting) target;
		String city = meeting.getCity();
		String description = meeting.getDescription();
		String address = meeting.getAddress();
		LocalDateTime date = meeting.getDate();
		LocalDateTime now = LocalDateTime.now();
		long diff = 0;
		if (date != null) {
			diff = ChronoUnit.MONTHS.between(now, date);
		}

		if (!StringUtils.hasLength(city)) {
			errors.rejectValue("city", REQUIRED, REQUIRED);
		}

		if (!city.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚ.' ']*$")) {
			errors.rejectValue("city", "La ciudad introducida no puede contener caracteres especiales ni números",
					"La ciudad introducida no puede contener caracteres especiales ni números.");
		}

		if (!StringUtils.hasLength(address) || address == null) {
			errors.rejectValue("address", REQUIRED, REQUIRED);
		}
		if (address.length() > 0 && address.length() < 3) {
			errors.rejectValue("address", "La dirección debe contener más de 3 caractéres",
					"La dirección debe contener más de 3 caractéres");
		}

		if (!address.matches("^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ.' 'º/]*$")) {
			errors.rejectValue("address", "Debe ser una dirección válida. Ej: 'C/ Azafrán nº7'",
					"Debe ser una dirección válida. Ej: 'C/ Azafrán nº7'");
		}

		if (!StringUtils.hasLength(description) || description == null) {
			errors.rejectValue("description", REQUIRED, REQUIRED);
		}

		if (!description.matches("^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ.,;+!¡¿?' ']*$")) {
			errors.rejectValue("description", "Debe contener solo letras y números",
					"Debe contener solo letras y números");
		}

		if (description.length() >= 300) {
			errors.rejectValue("description", "El tamaño de la descripción no puede ser superior a 300 caracteres",
					"El tamaño de la descripción no puede ser superior a 300 caracteres");
		}

		if (date == null) {
			errors.rejectValue("date", REQUIRED, REQUIRED);
		} else if (date.isBefore(LocalDateTime.now())) {
			errors.rejectValue("date", "La fecha debe ser posterior a la actual.",
					"La fecha debe ser posterior a la actual.");
		} else if (diff > 6) {
			errors.rejectValue("date", "Sólo se pueden establecer fechas en un rango inferior a 6 meses.",
					"Sólo se pueden establecer fechas en un rango inferior a 6 meses.");
		}
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Meeting.class.isAssignableFrom(clazz);
	}

}
