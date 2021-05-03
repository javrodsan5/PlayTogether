package net.playtogether.jpa.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import net.playtogether.jpa.entity.Invitation;
import net.playtogether.jpa.entity.Usuario;

public class InvitationValidator implements Validator {

	@Override
	public void validate(Object target, Errors errors) {
		Invitation invitation = (Invitation) target;

		Usuario receiver = invitation.getReceiver();

		if (receiver == null) {
			errors.rejectValue("selected_participant", "Debe elegir un usuario al que invitar",
					"Debe elegir un usuario al que invitar");
		}

	}

	@Override
	public boolean supports(Class<?> clazz) {
		return Invitation.class.isAssignableFrom(clazz);
	}

}
