package net.playtogether.jpa.service;

import java.util.Collection;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Match;
import net.playtogether.jpa.entity.Team;
import net.playtogether.jpa.repository.MatchRepository;

@Service
public class MatchService {
	
	MatchRepository matchRepository;
	
	public MatchService(MatchRepository matchRepository){
		this.matchRepository=matchRepository;
	}
	@Transactional()
	public void save(Match match)  {
		matchRepository.save(match);
	}
	
	@Transactional(readOnly=true)
	public Collection<Match> listMatches(){
		return matchRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public Match findMatchById(int id){
		return matchRepository.findById(id).orElse(null);
	}
	
	@Transactional(readOnly=true)
	public Collection<Match> listMatchesByChampionship(int championshipId){
		return matchRepository.listMatchesByChampionship(championshipId);
	}
	
	@Transactional(readOnly=true)
	public Collection<Team> findTeams(Integer championshipId){
		return matchRepository.findTeams(championshipId);
	}
	
	@Transactional(readOnly=true)
	public Collection<Match> findMatchesByTeamId(Integer teamId){
		return matchRepository.findMatchesByTeaId(teamId);
	}
	
	

}
