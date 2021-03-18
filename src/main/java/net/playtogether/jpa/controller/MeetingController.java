package net.playtogether.jpa.controller;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.service.MeetingService;

@Controller
public class MeetingController {

	@Autowired
	MeetingService meetingService;

	@GetMapping("/meetings/add")
	public String initCreationMeeting(ModelMap model) {
		Meeting meeting = new Meeting();
		model.addAttribute("meeting",meeting);
		return "addMeeting";
	}

	@PostMapping("/meetings/add")
	public String postCreationMeeting(@ModelAttribute("meeting") Meeting meeting, BindingResult result, ModelMap model) {
		if(!result.hasErrors()) {
			Sport sport = new Sport();
			sport.setId((long) 1);
			sport.setName("futbol");
			meeting.setSport(sport);
			meetingService.save(meeting);
			return "redirect:/";
		}else {
			return "addMeeting";
		}
	}
	
	@GetMapping("/meetings")
	public String listMeetings(ModelMap model) {
		Collection<Meeting>meetings= this.meetingService.listMeetings();
		model.addAttribute("meetings",meetings);
		return "listMeeting";
	}
	
	@GetMapping("/meetings/{meetingId}")
	public String meetingDetails(ModelMap model, @PathVariable ("meetingId") Integer meetingId) {
		Meeting meeting= this.meetingService.findMeetingById(meetingId);
		model.addAttribute("meeting",meeting);
		return "meetingDetails";
	}

}
