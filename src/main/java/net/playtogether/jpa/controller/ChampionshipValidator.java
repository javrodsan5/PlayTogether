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
		Integer maxTeams = championship.getMaxTeams();
		String name = championship.getName();

	
		
		if (!StringUtils.hasLength(city)) {
			errors.rejectValue("city", REQUIRED, REQUIRED);
		} else if (!city.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚ.' ']*$")) {
			errors.rejectValue("city", "Solo puede contener letras", "Solo puede contener letras");
		}
		
		if (!(StringUtils.hasLength(name))) {
			errors.rejectValue("name", REQUIRED, REQUIRED);
		}else if ( name.length() > 50 || name.length() < 3) {
			errors.rejectValue("name", "Debe tener entre 3 y 50 caracteres", "Debe tener entre 3 y 50 caracteres");
		}
		
		if (!name.matches("^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ.' ']*$")) {
			errors.rejectValue("name", "Solo puede contener letras y números", "Solo puede contener letras y números");
		}
		
	
		if (!StringUtils.hasLength(description) || description == null) {
			errors.rejectValue("description", REQUIRED, REQUIRED); 
		}
		
		if (!description.matches("^[a-zA-Z0-9ñÑáéíóúÁÉÍÓÚ.,;+!¡¿?' ']*$") || description == null) {
			errors.rejectValue("description", "Solo puede contener letras y números", "Solo puede contener letras y números"); 
		}

		if (startDate == null) {
			errors.rejectValue("startDate", REQUIRED, REQUIRED);
		}
		
		if (maxTeams == null) {
			errors.rejectValue("maxTeams", REQUIRED, REQUIRED);
		}
		
		if (startDate == null) {
			errors.rejectValue("startDate", "No ha introducido la fecha correctamente.",
					"No ha introducido la fecha correctamente.");
			
		}
		if (finishDate == null) {
			errors.rejectValue("finishDate", "No ha introducido la fecha correctamente", "No ha introducido la fecha correctamente");
		}
		

		else if (startDate.isBefore(LocalDate.now())) {
			errors.rejectValue("startDate", "La fecha debe ser posterior a la actual.",
					"La fecha debe ser posterior a la actual.");
		}
		
		 else if (finishDate.isBefore(LocalDate.now())) {
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
