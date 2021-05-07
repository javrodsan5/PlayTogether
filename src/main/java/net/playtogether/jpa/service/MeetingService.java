package net.playtogether.jpa.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.MeetingCategory;
import net.playtogether.jpa.repository.MeetingCategoryRepository;
import net.playtogether.jpa.repository.MeetingRepository;

@Service
public class MeetingService {

	MeetingRepository meetingRepository;

	MeetingCategoryRepository categoryRepository;

	public MeetingService(MeetingRepository meetingRepository, MeetingCategoryRepository categoryRepository) {
		this.meetingRepository = meetingRepository;
		this.categoryRepository = categoryRepository;
	}

	@Transactional()
	public void save(Meeting meeting) {
		meetingRepository.save(meeting);
	}

	@Transactional(readOnly = true)
	public Collection<Meeting> listMeetings() {
		return meetingRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Meeting findMeetingById(int id) {
		return meetingRepository.findById(id).orElse(null);
	}

	@Transactional(readOnly = true)
	public Collection<Meeting> listMeetingsBySport(int sportId) {
		return meetingRepository.listMeetingsBySport(sportId);
	}

	@Transactional(readOnly = true)
	public Collection<Meeting> findMeetingThisMonthToUser(int userId) {
		return meetingRepository.findMeetingThisMonthToUser(userId);
	}

	@Transactional
	public List<MeetingCategory> listCategories() {
		List<MeetingCategory> res = new ArrayList<>();
		this.categoryRepository.findAll().forEach(res::add);

		return res;
	}

	@Transactional
	public MeetingCategory findCategoryById(Integer id) {
		return this.categoryRepository.findById(id).orElse(null);
	}

	@Transactional
	public List<Meeting> findByCategory(String category) {
		return this.meetingRepository.findMeetingsByCategory(category);
	}

}
