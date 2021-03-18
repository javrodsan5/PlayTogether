package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Statistic;

@Repository
public interface StatisticRepository extends CrudRepository<Statistic, Integer> {
 
}
