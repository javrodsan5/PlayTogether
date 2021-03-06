package net.playtogether.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.MeetingCategory;

@Repository
public interface MeetingCategoryRepository extends CrudRepository<MeetingCategory, Integer> {
    
}
