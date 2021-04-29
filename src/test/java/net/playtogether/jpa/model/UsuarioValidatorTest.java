package net.playtogether.jpa.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import net.playtogether.jpa.controller.MeetingValidator;
import net.playtogether.jpa.controller.UsuarioValidator;
import net.playtogether.jpa.entity.Authorities;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.entity.UserType;
import net.playtogether.jpa.entity.Usuario;

import static org.assertj.core.api.Assertions.assertThat;

public class UsuarioValidatorTest {

	private Validator createValidator() {
		LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
		localValidatorFactoryBean.afterPropertiesSet();
		return localValidatorFactoryBean;
	}

	@Test
	void shouldNotValidateWhenNameEmpty() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo("fercadu@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Validator validator = createValidator();
		Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Usuario> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("name");
		assertThat(violation.getMessage()).isEqualTo("size must be between 3 and 50");

	}

	@Test
	void shouldNotValidateWhenBirthDateNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(null);
		usuario.setCorreo("fercadu@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Validator validator = createValidator();
		Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Usuario> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("birthdate");
		assertThat(violation.getMessage()).isEqualTo("must not be null");

	}

	@Test
	void shouldNotValidateWhenPhoneNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo("fercadu@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone(null);
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Validator validator = createValidator();
		Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Usuario> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("phone");
		assertThat(violation.getMessage()).isEqualTo("must not be null");

	}

	@Test
	void shouldNotValidateWhenPhoneNotPattern() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo("fercadu@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665433");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Validator validator = createValidator();
		Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Usuario> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("phone");
		String c = "\"";
		assertThat(violation.getMessage()).isEqualTo("must match " + c + "[0-9]{9}" + c);

	}

	@Test
	void shouldNotValidateWhenCorreoNotPattern() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo("ferki@");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Validator validator = createValidator();
		Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Usuario> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("correo");
		assertThat(violation.getMessage()).isEqualTo("must be a well-formed email address");

	}

	@Test
	void shouldNotValidateWhenCorreoNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo(null);
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Validator validator = createValidator();
		Set<ConstraintViolation<Usuario>> constraintViolations = validator.validate(usuario);
		assertThat(constraintViolations.size()).isEqualTo(1);
		ConstraintViolation<Usuario> violation = constraintViolations.iterator().next();
		assertThat(violation.getPropertyPath().toString()).isEqualTo("correo");
		assertThat(violation.getMessage()).isEqualTo("must not be null");

	}

	@Test
	void shouldNotValidateWhenNameNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo("ferki@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName(null);
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("name").getDefaultMessage())
				.isEqualTo("Campo requerido" + " Debe contener entre 1 y 50 caracteres");

	}
	
	@Test
	void shouldNotValidateWhenNameNoLength() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo("ferki@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("name").getDefaultMessage())
				.isEqualTo("Campo requerido" + " Debe contener entre 1 y 50 caracteres");

	}
	
	@Test
	void shouldNotValidateWhenNameLengthMore50() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo("ferki@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Francisco de las Flores Cortés de la Sierra Morena Cayetano Rivera");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("name").getDefaultMessage())
				.isEqualTo("Campo requerido" + " Debe contener entre 1 y 50 caracteres");

	}
	
	@Test
	void shouldNotValidateWhenCorreoNoLength() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo("");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("correo").getDefaultMessage())
				.isEqualTo("Campo requerido");

	}
	
	@Test
	void shouldNotValidateWhenCorreoNoPattern() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo("woma@");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("correo").getDefaultMessage())
				.isEqualTo("Tu email debe tener un formato correcto");

	}
	
	@Test
	void shouldNotValidateWhenPhoneLength() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo("woma@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("6656");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("phone").getDefaultMessage())
				.isEqualTo("El teléfono introducido no es válido");

	}
	
	@Test
	void shouldNotValidateWhenPhoneNotNumeric() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo("woma@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("aaa");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("phone").getDefaultMessage())
				.isEqualTo(" El teléfono debe ser númerico");

	}
	
	@Test
	void shouldNotValidateWhenPhoneMore9Numbers() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(1999, 11, 19));
		usuario.setCorreo("woma@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("66666666666666666");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("phone").getDefaultMessage())
				.isEqualTo("El teléfono debe tener 9 caracteres.");

	}
	
	@Test
	void shouldNotValidateWhenBirthdateNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(null);
		usuario.setCorreo("woma@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("birthdate").getDefaultMessage())
				.isEqualTo("Campo requerido");

	}
	
	@Test
	void shouldNotValidateWhenBirthdateAfterNow() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(2059, 11, 19));
		usuario.setCorreo("woma@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("birthdate").getDefaultMessage())
				.isEqualTo("La fecha de nacimiento debe ser anterior a la actual");

	}
	
	@Test
	void shouldNotValidateWhenBirthdateYounger() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(2019, 11, 19));
		usuario.setCorreo("woma@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("birthdate").getDefaultMessage())
				.isEqualTo("Debes ser mayor de edad para registrarte");

	}
	
	@Test
	void shouldNotValidateWhenUsernameNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(2019, 11, 19));
		usuario.setCorreo("woma@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("user.username").getDefaultMessage())
		.isEqualTo("Campo requerido");

	}
	
	@Test
	void shouldNotValidateWhenUsernameMore15Chr() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercaduDeLaSierraCadiz");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(2019, 11, 19));
		usuario.setCorreo("woma@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("user.username").getDefaultMessage())
		.isEqualTo("El nombre de usuario no puede contener más de 15 caracteres");

	}
	
	@Test
	void shouldNotValidateWhenUsernameNoPattern() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("!!!");
		user.setEnabled(true);
		user.setPassword("Contrasenya1");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(2019, 11, 19));
		usuario.setCorreo("woma@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("user.username").getDefaultMessage())
		.isEqualTo("Solo puede contener letras sin tildes y números");

	}
	
	@Test
	void shouldNotValidateWhenPasswordEmpty() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(2019, 11, 19));
		usuario.setCorreo("woma@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("665667543");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("user.password").getDefaultMessage())
		.isEqualTo("Campo requerido");

	}
	
	@Test
	void shouldNotValidateWhenPhoneNoLength() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Usuario usuario = new Usuario();

		User user = new User();

		UserType u = new UserType();
		u.setName("Premium");

		user.setUsername("fercadu");
		user.setEnabled(true);
		user.setPassword("");
		Set<Authorities> s = new HashSet<Authorities>();
		user.setAuthorities(s);

		usuario.setAccept(true);
		usuario.setBirthdate(LocalDate.of(2019, 11, 19));
		usuario.setCorreo("woma@hotmail.com");
		usuario.setDescription("Hola q tal");
		usuario.setName("Fernandito");
		usuario.setPhone("");
		usuario.setPuntos(25);
		usuario.setType(u);
		usuario.setUser(user);

		Errors errors = new BeanPropertyBindingResult(usuario, "usuario");

		UsuarioValidator validator = new UsuarioValidator();
		validator.validate(usuario, errors);
		assertThat(errors.getFieldError("phone").getDefaultMessage())
		.isEqualTo("Campo requerido");

	}


}
