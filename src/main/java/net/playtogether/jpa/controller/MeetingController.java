package net.playtogether.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.service.MeetingService;

@Controller
public class MeetingController {

	@Autowired
	MeetingService meetingService;

	@GetMapping("/meetings/add")
	public String initCreationMeeting(ModelMap model) {
		Meeting meeting = new Meeting();
		model.put("meeting", meeting);
		return "addMeeting";
	}

	@PostMapping("/meetings/add")
	public String postCreationMeeting(Meeting meeting, BindingResult result, ModelMap model) {
		if(!result.hasErrors()) {
			System.out.println("entra");
			meetingService.save(meeting);
		}else {
			return "redirect:/asdf";
		}
		return "redirect:/";
	}

}
