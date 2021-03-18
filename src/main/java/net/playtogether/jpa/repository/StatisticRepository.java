package net.playtogether.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Statistic;

@Repository
public interface StatisticRepository extends JpaRepository<Statistic, Long> {
 
}
