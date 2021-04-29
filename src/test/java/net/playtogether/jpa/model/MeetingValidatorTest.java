package net.playtogether.jpa.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Locale;

import org.junit.jupiter.api.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import net.playtogether.jpa.controller.MeetingValidator;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.Usuario;

import static org.assertj.core.api.Assertions.assertThat;

public class MeetingValidatorTest {

	@Test
	void shouldNotValidateWhenAddressNoLength() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Meeting meeting = new Meeting();

		meeting.setAddress("");
		meeting.setCity("Arcos de la frontera");
		meeting.setCreationDate(LocalDate.now());
		meeting.setDate(LocalDateTime.of(2021, 06, 20, 20, 50));
		meeting.setDescription("Partidazo de locos en Woma");
		meeting.setMeetingCreator(new Usuario());
		meeting.setSport(new Sport());
		meeting.setNumberOfPlayers(12);

		Errors errors = new BeanPropertyBindingResult(meeting, "meeting");

		MeetingValidator validator = new MeetingValidator();
		validator.validate(meeting, errors);
		assertThat(errors.getFieldError("address").getDefaultMessage()).isEqualTo("Campo requerido.");
	}

	@Test
	void shouldNotValidateWhenDescriptionNoLength() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Meeting meeting = new Meeting();

		meeting.setAddress("Pabellon municipal");
		meeting.setCity("Arcos de la frontera");
		meeting.setCreationDate(LocalDate.now());
		meeting.setDate(LocalDateTime.of(2021, 06, 20, 20, 50));
		meeting.setDescription("");
		meeting.setMeetingCreator(new Usuario());
		meeting.setSport(new Sport());
		meeting.setNumberOfPlayers(12);

		Errors errors = new BeanPropertyBindingResult(meeting, "meeting");

		MeetingValidator validator = new MeetingValidator();
		validator.validate(meeting, errors);
		assertThat(errors.getFieldError("description").getDefaultMessage()).isEqualTo("Campo requerido.");
	}

	@Test
	void shouldNotValidateWhenDescriptionOnlyNumbersAndLetters() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Meeting meeting = new Meeting();

		meeting.setAddress("Pabellon municipal");
		meeting.setCity("Arcos de la frontera");
		meeting.setCreationDate(LocalDate.now());
		meeting.setDate(LocalDateTime.of(2021, 06, 20, 20, 50));
		meeting.setDescription("@@@*");
		meeting.setMeetingCreator(new Usuario());
		meeting.setSport(new Sport());
		meeting.setNumberOfPlayers(12);

		Errors errors = new BeanPropertyBindingResult(meeting, "meeting");

		MeetingValidator validator = new MeetingValidator();
		validator.validate(meeting, errors);
		assertThat(errors.getFieldError("description").getDefaultMessage())
				.isEqualTo("Debe contener solo letras y números");
	}

	@Test
	void shouldNotValidateWhenDescriptionHasMore300chr() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Meeting meeting = new Meeting();

		meeting.setAddress("Pabellon municipal");
		meeting.setCity("Arcos de la frontera");
		meeting.setCreationDate(LocalDate.now());
		meeting.setDate(LocalDateTime.of(2021, 06, 20, 20, 50));
		meeting.setDescription(
				"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
		meeting.setMeetingCreator(new Usuario());
		meeting.setSport(new Sport());
		meeting.setNumberOfPlayers(12);

		Errors errors = new BeanPropertyBindingResult(meeting, "meeting");

		MeetingValidator validator = new MeetingValidator();
		validator.validate(meeting, errors);
		assertThat(errors.getFieldError("description").getDefaultMessage())
				.isEqualTo("El tamaño de la descripción no puede ser superior a 300 caracteres");
	}

	@Test
	void shouldNotValidateWhenDateNull() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Meeting meeting = new Meeting();

		meeting.setAddress("Pabellon municipal");
		meeting.setCity("Arcos de la frontera");
		meeting.setCreationDate(LocalDate.now());
		meeting.setDate(null);
		meeting.setDescription("Partidazo de locos en Woma");
		meeting.setMeetingCreator(new Usuario());
		meeting.setSport(new Sport());
		meeting.setNumberOfPlayers(12);

		Errors errors = new BeanPropertyBindingResult(meeting, "meeting");

		MeetingValidator validator = new MeetingValidator();
		validator.validate(meeting, errors);
		assertThat(errors.getFieldError("date").getDefaultMessage()).isEqualTo("Campo requerido.");
	}

	@Test
	void shouldNotValidateWhenDateIsBeforeNow() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Meeting meeting = new Meeting();

		meeting.setAddress("Pabellon municipal");
		meeting.setCity("Arcos de la frontera");
		meeting.setCreationDate(LocalDate.now());
		meeting.setDate(LocalDateTime.of(2019, 12, 12, 12, 12));
		meeting.setDescription("Partidazo de locos en Woma");
		meeting.setMeetingCreator(new Usuario());
		meeting.setSport(new Sport());
		meeting.setNumberOfPlayers(12);

		Errors errors = new BeanPropertyBindingResult(meeting, "meeting");

		MeetingValidator validator = new MeetingValidator();
		validator.validate(meeting, errors);
		assertThat(errors.getFieldError("date").getDefaultMessage())
				.isEqualTo("La fecha debe ser posterior a la actual.");
	}
	
	@Test
	void shouldNotValidateWhenDateRange6Months() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Meeting meeting = new Meeting();

		meeting.setAddress("Pabellon municipal");
		meeting.setCity("Arcos de la frontera");
		meeting.setCreationDate(LocalDate.now());
		meeting.setDate(LocalDateTime.of(2059, 12, 12, 12, 12));
		meeting.setDescription("Partidazo de locos en Woma");
		meeting.setMeetingCreator(new Usuario());
		meeting.setSport(new Sport());
		meeting.setNumberOfPlayers(12);

		Errors errors = new BeanPropertyBindingResult(meeting, "meeting");

		MeetingValidator validator = new MeetingValidator();
		validator.validate(meeting, errors);
		assertThat(errors.getFieldError("date").getDefaultMessage())
				.isEqualTo("Sólo se pueden establecer fechas en un rango inferior a 6 meses.");
	}

	@Test
	void shouldNotValidateWhenCityNoLength() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Meeting meeting = new Meeting();

		meeting.setAddress("Pabellon municipal");
		meeting.setCity("");
		meeting.setCreationDate(LocalDate.now());
		meeting.setDate(LocalDateTime.of(2021, 06, 20, 20, 50));
		meeting.setDescription("Partidazo de locos en Woma");
		meeting.setMeetingCreator(new Usuario());
		meeting.setSport(new Sport());
		meeting.setNumberOfPlayers(12);

		Errors errors = new BeanPropertyBindingResult(meeting, "meeting");

		MeetingValidator validator = new MeetingValidator();
		validator.validate(meeting, errors);
		assertThat(errors.getFieldError("city").getDefaultMessage()).isEqualTo("Campo requerido.");
	}

	@Test
	void shouldNotValidateWhenCityNoPattern() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Meeting meeting = new Meeting();

		meeting.setAddress("Pabellon municipal");
		meeting.setCity("!!");
		meeting.setCreationDate(LocalDate.now());
		meeting.setDate(LocalDateTime.of(2021, 06, 20, 20, 50));
		meeting.setDescription("Partidazo de locos en Woma");
		meeting.setMeetingCreator(new Usuario());
		meeting.setSport(new Sport());
		meeting.setNumberOfPlayers(12);

		Errors errors = new BeanPropertyBindingResult(meeting, "meeting");

		MeetingValidator validator = new MeetingValidator();
		validator.validate(meeting, errors);
		assertThat(errors.getFieldError("city").getDefaultMessage())
				.isEqualTo("La ciudad introducida no puede contener caracteres especiales ni números.");
	}

	@Test
	void shouldNotValidateWhenAddressLength0to3() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Meeting meeting = new Meeting();

		meeting.setAddress("s");
		meeting.setCity("Arcos de la Fra");
		meeting.setCreationDate(LocalDate.now());
		meeting.setDate(LocalDateTime.of(2021, 06, 20, 20, 50));
		meeting.setDescription("Partidazo de locos en Woma");
		meeting.setMeetingCreator(new Usuario());
		meeting.setSport(new Sport());
		meeting.setNumberOfPlayers(12);

		Errors errors = new BeanPropertyBindingResult(meeting, "meeting");

		MeetingValidator validator = new MeetingValidator();
		validator.validate(meeting, errors);
		assertThat(errors.getFieldError("address").getDefaultMessage())
				.isEqualTo("La dirección debe contener más de 3 caractéres");
	}

	@Test
	void shouldNotValidateWhenAddressNotValid() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
		Meeting meeting = new Meeting();

		meeting.setAddress("!!!");
		meeting.setCity("Arcos de la Fra");
		meeting.setCreationDate(LocalDate.now());
		meeting.setDate(LocalDateTime.of(2021, 06, 20, 20, 50));
		meeting.setDescription("Partidazo de locos en Woma");
		meeting.setMeetingCreator(new Usuario());
		meeting.setSport(new Sport());
		meeting.setNumberOfPlayers(12);

		Errors errors = new BeanPropertyBindingResult(meeting, "meeting");

		MeetingValidator validator = new MeetingValidator();
		validator.validate(meeting, errors);
		assertThat(errors.getFieldError("address").getDefaultMessage())
				.isEqualTo("Debe ser una dirección válida. Ej: 'C/ Azafrán nº7'");
	}

}
