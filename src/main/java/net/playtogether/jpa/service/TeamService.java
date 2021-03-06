package net.playtogether.jpa.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.repository.TeamRepository;

@Service
public class TeamService {

	TeamRepository teamRepository;

	public TeamService(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Transactional(readOnly = true)
	public Team findTeamById(int id) {
		return teamRepository.findById(id).orElse(null);
	}

	@Transactional
	public void delete(Team team) {
		this.teamRepository.delete(team);
	}

	@Transactional
	public void deleteAll(List<Team> teams) {
		this.teamRepository.deleteAll(teams);
	}

}
