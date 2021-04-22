package net.playtogether.jpa.controller;

import java.time.LocalDate;
import java.time.Period;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.service.UsuarioService;

public class UsuarioValidator implements Validator {

	private static final String REQUIRED = "Campo requerido";

	private static final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" + "\\@"
			+ "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" + "(" + "\\." + "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" + ")+");
	
	private static final Pattern TELEPHONE_PATTERN = Pattern.compile("[0-9]{9}");
	
	public UsuarioService usuarioService;


	private static boolean checkPassword(String password) {
		boolean tieneMayus = false;
		boolean tieneMinus = false;
		boolean tieneNumero = false;
		for (int i = 0; i < password.length(); i++) {
			char ch = password.charAt(i);
			if (Character.isDigit(ch)) {
				tieneNumero = true;
			}
			if (Character.isUpperCase(ch)) {
				tieneMayus = true;
			}
			if (Character.isLowerCase(ch)) {
				tieneMinus = true;
			}
		}
		if (tieneNumero && tieneMayus && tieneMinus) {
			return true;
		}
		return false;

	}

	private static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	private static boolean esAdulto(LocalDate fechaNac) {
		if (Period.between(fechaNac, LocalDate.now()).getYears() >= 18) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void validate(Object target, Errors errors) {
		Usuario usuario = (Usuario) target;
		String nombre = usuario.getName();
		String correo = usuario.getCorreo();
		String phone = usuario.getPhone();
		String username = usuario.getUser().getUsername();
		String password = usuario.getUser().getPassword();
		String description = usuario.getDescription();
		LocalDate fechaNac = usuario.getBirthdate();

		if (nombre == null || !StringUtils.hasLength(nombre) || nombre.length() > 50) {
			errors.rejectValue("name", REQUIRED + " Debe contener entre 1 y 50 caracteres",
					REQUIRED + " Debe contener entre 1 y 50 caracteres");
		}

		if (nombre == null || !nombre.matches("^[a-zA-ZñÑáéíóúÁÉÍÓÚ.' ']*$")) {
			errors.rejectValue("name", "Solo puede contener letras", "Solo puede contener letras");
		}

		if (correo == null || !StringUtils.hasLength(correo)) {
			errors.rejectValue("correo", REQUIRED, REQUIRED);

		} else if (!EMAIL_ADDRESS_PATTERN.matcher(correo).matches()) {
			errors.rejectValue("correo", "Tu email debe tener un formato correcto",
					"Tu email debe tener un formato correcto");
		}

		if (!StringUtils.hasLength(phone)) {
			errors.rejectValue("phone", REQUIRED, REQUIRED);
		}
		
		if(phone.length()>9) {
			errors.rejectValue("phone", "El teléfono debe tener 9 caracteres.", "El teléfono debe tener 9 caracteres.");
		}
		
		if (!isNumeric(phone)) {
			errors.rejectValue("phone", " El teléfono debe ser númerico", " El teléfono debe ser númerico");
		}

		if (!TELEPHONE_PATTERN.matcher(phone).matches()) {
			errors.rejectValue("phone","El teléfono introducido no es válido", "El teléfono introducido no es válido");
		}

		if (fechaNac == null) {
			errors.rejectValue("birthdate", REQUIRED, REQUIRED);
		} else {
			if (fechaNac.isAfter(LocalDate.now())) {
				errors.rejectValue("birthdate", "La fecha de nacimiento debe ser anterior a la actual",
						"La fecha de nacimiento debe ser anterior a la actual");
			} else {
				if (!esAdulto(fechaNac)) {
					errors.rejectValue("birthdate", "Debes ser mayor de edad para registrarte",
							"Debes ser mayor de edad para registrarte");
				}
			}
		}

		if (username == null || !StringUtils.hasLength(username)) {
			errors.rejectValue("user.username", REQUIRED, REQUIRED);
		}
		
		if (username.length() > 15) {
			errors.rejectValue("user.username", "El nombre de usuario no puede contener más de 15 caracteres",
					"El nombre de usuario no puede contener más de 15 caracteres");
		}

		if ((!username.matches("^[a-zA-Z0-9ñÑ.' ']*$"))) {
			errors.rejectValue("user.username",
					"Solo puede contener letras sin tildes y números",
					"Solo puede contener letras sin tildes y números");
		}

		if (password != null) {
			if (!StringUtils.hasLength(password)) {
				errors.rejectValue("user.password", REQUIRED, REQUIRED);
			}

			if (password.length() < 5) {
				errors.rejectValue("user.password", "La contraseña debe tener más de 5 caracteres",
						"La contraseña debe tener más de 5 caracteres");
			}

			if (!checkPassword(password)) {
				errors.rejectValue("user.password",
						"La contraseña debe tener al menos una mayúscula, un número y una minúscula",
						"La contraseña debe tener al menos una mayúscula, un número y una minúscula");
			}
		}
		
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Usuario.class.isAssignableFrom(clazz);

	}

}
