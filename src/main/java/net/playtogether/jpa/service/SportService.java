package net.playtogether.jpa.service;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import net.playtogether.jpa.entity.Sport;
import net.playtogether.jpa.repository.SportRepository;

@Service
public class SportService {

	@Autowired
	SportRepository sportRepository;

	@Transactional
	public Collection<Sport> findAll() throws DataAccessException {
		return sportRepository.findAll();
	}

	@Transactional
	public Sport findSportByName(String name) throws DataAccessException {
		return sportRepository.findSportByName(name);
	}

	public Collection<Sport> findAllSportsByType(String sportType) throws DataAccessException {
		return sportRepository.findAllSportsByType(sportType);

	}

	public Sport findSportById(Integer id) throws DataAccessException {
		return sportRepository.findSportById(id);
	}

}
