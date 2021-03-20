package net.playtogether.jpa.service;

import java.util.Collection;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Championship;
import net.playtogether.jpa.repository.ChampionshipRepository;

@Service
public class ChampionshipService {
	
	private ChampionshipRepository championshipRepository;
	
	@Autowired
	public ChampionshipService(ChampionshipRepository championshipRepository) {
		this.championshipRepository = championshipRepository;
		
	}
	
	
	@Transactional(readOnly=true)
	public Championship findChampionshipId( int id) throws DataAccessException {
		return this.championshipRepository.findById(id).orElse(null);
	}

	@Transactional
	public boolean save(final Championship championship) throws DataAccessException{

			this.championshipRepository.save(championship);
			return true;
		
	}
	
	@Transactional(readOnly=true)
	public Collection<Championship> listChampionship(){
		return championshipRepository.findAll();
	}
	
}
