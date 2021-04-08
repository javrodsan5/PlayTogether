package net.playtogether.jpa.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.repository.TeamRepository;
import net.playtogether.jpa.repository.UserRepository;

@Service
public class TeamService {

	TeamRepository teamRepository;

	public TeamService(TeamRepository teamRepository){
		this.teamRepository=teamRepository;
	}

	
	@Transactional(readOnly=true)
	public Team findTeamById(int id){
		return teamRepository.findById(id).orElse(null);
	}
	
}
