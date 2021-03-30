package net.playtogether.jpa.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Meeting;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.User;
import net.playtogether.jpa.repository.ChampionshipRepository;
import net.playtogether.jpa.repository.TeamRepository;

@Service
public class ChampionshipService {

	private ChampionshipRepository championshipRepository;
	private TeamRepository teamRepository;

	@Autowired
	public ChampionshipService(ChampionshipRepository championshipRepository, TeamRepository teamRepository) {
		this.championshipRepository = championshipRepository;
		this.teamRepository = teamRepository;
	}

	@Transactional(readOnly = true)
	public Championship findChampionshipId(int id) throws DataAccessException {
		return this.championshipRepository.findById(id).orElse(null);
	}

	@Transactional
	public boolean save(final Championship championship) throws DataAccessException {

		this.championshipRepository.save(championship);
		return true;

	}

	@Transactional(readOnly = true)
	public Collection<Championship> listChampionship() {
		return championshipRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Collection<Championship> listChampionshipsBySport(int sportId) {
		return championshipRepository.listChampionshipsBySport(sportId);
	}
	
	@Transactional(readOnly=true)
	public User findUsersById(Integer id) throws DataAccessException {
		return this.teamRepository.findUserById(id);
	}
	
	@Transactional(readOnly=true)
	public List<Team> findTeamsByChampionshipId(Integer id) throws DataAccessException {
		return this.teamRepository.findTeamsByChampionshipId(id);
	}
	
	@Transactional
	public Integer countTeams() {
		return (int) this.teamRepository.count();
	}

	@Transactional(readOnly = true)
	public Team findTeamId(int id) throws DataAccessException {
		return this.teamRepository.findById(id).orElse(null);
	}

	@Transactional
	public boolean save(final Team team) throws DataAccessException {
		this.teamRepository.save(team);
		return true;
	}

	@Transactional(readOnly = true)
	public Collection<Team> listTeams() {
		return teamRepository.findAll();
	}

	@Transactional(readOnly = true)
	public List<User> findUserByNameOrUsername(String user) throws DataAccessException {
		return this.teamRepository.findUserByNameOrUsername(user);
	}

}
