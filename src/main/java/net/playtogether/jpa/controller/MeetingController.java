package net.playtogether.jpa.controller;

import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.service.MeetingService;
import net.playtogether.jpa.service.SportService;

@Controller
public class MeetingController {

	@Autowired
	MeetingService meetingService;
	
	@Autowired
	SportService sportService;
	
	@InitBinder("meeting")
	public void initMeetingBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new MeetingValidator());
	}


	@GetMapping("/meetings/add")
	public String initCreationMeeting(ModelMap model) {
		Meeting meeting = new Meeting();
		Collection<Sport> listaDeportes = this.sportService.findAll();
		model.put("meeting", meeting);
		model.put("listaDeportes", listaDeportes);
		return "meetings/addMeeting";
	}

	@PostMapping("/meetings/add")
	public String postCreationMeeting(@Valid Meeting meeting, BindingResult result) {
		if (result.hasErrors()) {
			return "meetings/addMeeting";
		} else {
			meetingService.save(meeting);
			return "redirect:/";
		}
	}

	@GetMapping("/meetings")
	public String listMeetings(ModelMap model) {
		Collection<Meeting> meetings = this.meetingService.listMeetings();
		model.addAttribute("meetings", meetings);
		return "meetings/listMeeting";
	}

	@GetMapping("/meetings/{meetingId}")
	public String meetingDetails(ModelMap model, @PathVariable("meetingId") Integer meetingId) {
		Meeting meeting = this.meetingService.findMeetingById(meetingId);
		model.addAttribute("meeting", meeting);
		return "meetings/meetingDetails";
	}

}
