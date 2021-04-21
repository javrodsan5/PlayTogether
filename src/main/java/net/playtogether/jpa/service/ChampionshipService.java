package net.playtogether.jpa.service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.entity.Usuario;
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
	public Usuario findUsersById(Integer id) throws DataAccessException {
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
	public List<Usuario> findUserByNameOrUsername(String user) throws DataAccessException {
		return this.teamRepository.findUserByNameOrUsername(user);
	}

	@Transactional
	public void delete(Championship championship) {
		this.championshipRepository.delete(championship);
	}

	@Transactional
	public void deleteAll(List<Championship> championships) {
		this.championshipRepository.deleteAll(championships);
	}

	@Transactional(readOnly = true)
	public Collection<Usuario> findParticipantsChampionship(int championshipId) throws DataAccessException {
		return this.championshipRepository.findParticipantsChampionship(championshipId);
	}

	@Transactional
	public Match getUltimoPartido(Championship championship) {
		List<Match> matches = championship.getMatches().stream().sorted(Comparator.comparing(Match::getId)).collect(Collectors.toList());
		
		switch (championship.getMaxTeams()){
		case 4:
			return matches.get(2);
		case 8:
			return matches.get(6);
		case 16:
			return matches.get(14);
		default:
			return null;
		}
		
		
	}

	@Transactional
	public Team getGanadorPartido(Match partido) {
		if (partido.getPuntos1() == partido.getPuntos3()
				&& partido.getPuntos2() == partido.getPuntos4()) {
			if (partido.getPuntos1() > partido.getPuntos2()) {
				return partido.getTeam1();
			} else {
				return partido.getTeam2();
			}
		}else {
			return null;
		}
	}

	@Transactional
	public Boolean existeChampionship(Integer championshipId) {
		return this.championshipRepository.findAll().stream().anyMatch(c -> c.getId().equals(championshipId));

	}
	
	@Transactional
	public Boolean coincideResultados(Match match) {
		Boolean res = true;
		
		if(match.getPuntos1() != match.getPuntos3() || match.getPuntos2() != match.getPuntos4()) {
			res = false;
		}
		
		return res;

	}

}
