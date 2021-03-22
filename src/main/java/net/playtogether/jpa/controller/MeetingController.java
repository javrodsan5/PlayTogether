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
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.service.MeetingService;
import net.playtogether.jpa.service.SportService;
import net.playtogether.jpa.service.UserService;

@Controller
public class MeetingController {

	@Autowired
	MeetingService meetingService;

	@Autowired
	UserService userService;

	@Autowired
	SportService sportService;

	@InitBinder("meeting")
	public void initMeetingBinder(WebDataBinder dataBinder) {
		dataBinder.setValidator(new MeetingValidator());
	}

	@GetMapping("/sports/{sportId}/meetings/add")
	public String initCreationMeeting(ModelMap model,@PathVariable("sportId") Integer sportId) {
		model.put("meeting", new Meeting());
		model.put("deporte", sportId);
		return "meetings/addMeeting";
	}

	@PostMapping("/sports/{sportId}/meetings/add")
	public String postCreationMeeting(@Valid Meeting meeting, BindingResult result, ModelMap model,@PathVariable("sportId") Integer sportId) {
		if (result.hasErrors()) {
			model.put("deporte", sportId);
			return "meetings/addMeeting";
		} else {
			meetingService.save(meeting);
			return "redirect:/sports/"+sportId+"/meetings";
		}

	}

	@GetMapping("/sports/{sportId}/meetings")
	public String listMeetings(ModelMap model,@PathVariable("sportId") Integer sportId) {
		Collection<Meeting> meetings = this.meetingService.listMeetingsBySport(sportId);
		model.addAttribute("meetings", meetings);
		model.addAttribute("deporte",sportId);
		return "meetings/listMeeting";
	}

	@GetMapping("/sports/{sportId}/meetings/{meetingId}")
	public String meetingDetails(ModelMap model, @PathVariable("meetingId") Integer meetingId) {
		Meeting meeting = this.meetingService.findMeetingById(meetingId);
		model.addAttribute("meeting", meeting);
		Boolean b = true;
		User u = this.userService.findUserById(1);
		
		if (meeting.getParticipants().stream().noneMatch(x -> x.getId() == u.getId())) {
			b = false;
		}
		model.addAttribute("existe", b);
		
		return "meetings/meetingDetails";
	}

	@GetMapping("/meetings/{meetingId}/join")
	public String meetingJoin(ModelMap model, @PathVariable("meetingId") Integer meetingId) {
		Meeting meeting = this.meetingService.findMeetingById(meetingId);
		User u = this.userService.findUserById(1);
		
		if (meeting.getParticipants().stream().noneMatch(x -> x.getId() == u.getId())) {

			List<User> list = meeting.getParticipants();
			list.add(u);
			meeting.setParticipants(list);

			this.meetingService.save(meeting);
			
		
		}
		
		return meetingDetails(model, meetingId);
	}

}
