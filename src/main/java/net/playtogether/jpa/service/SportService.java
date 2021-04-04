package net.playtogether.jpa.service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.entity.SportType;
import net.playtogether.jpa.repository.SportRepository;

@Service
public class SportService {

	private SportRepository sportRepository;
	
	@Autowired
	public SportService(SportRepository sportRepository) {
		this.sportRepository = sportRepository;
	}

	@Transactional(readOnly=true)
	public Collection<Sport> findAll() throws DataAccessException{
		return sportRepository.findAll();
	}	
	
	@Transactional(readOnly=true)
	public Sport findSportByName(String name) throws DataAccessException {
		return sportRepository.findSportByName(name);
	}

	@Transactional(readOnly=true)
	public Collection<Sport> findAllSportsByType(SportType sportType) throws DataAccessException {
		return sportRepository.findAllSportsByType(sportType);

	}

	@Transactional(readOnly=true)
	public Sport findSportById(Integer id) throws DataAccessException {
		return sportRepository.findSportById(id);
	}

}
