package net.playtogether.jpa.controller;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.playtogether.jpa.entity.Team;

public class TeamValidator implements Validator {


	private static final String REQUIRED = "Campo requerido.";

	@Override
	public void validate(Object target, Errors errors) {
		Team team = (Team) target;
		String name = team.getName();
		
		Boolean res = !StringUtils.hasLength(name);

		if (res) {
			errors.rejectValue("name", REQUIRED, REQUIRED);
		}

		if (!res && (name.length() > 50 || name.length() < 3)) {
			errors.rejectValue("name", "El nombre del equipo debe tener entre 3 y 50 carácteres.",
					"El nombre del equipo debe tener entre 3 y 50 carácteres.");
		}

	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Team.class.isAssignableFrom(clazz);
	}

}