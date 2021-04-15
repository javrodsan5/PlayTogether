package net.playtogether.jpa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import net.playtogether.jpa.entity.Usuario;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

	@Query("SELECT u FROM Usuario u")
	List<Usuario> findAll();

	@Query("SELECT u FROM Usuario u where u.correo = ?1")
	Optional<Usuario> findUsuarioByCorreo(String correo);

	@Query("SELECT u FROM Usuario u where u.user.username = ?1")
	Optional<Usuario> findUsuarioByUsername(String username);

	@Query("SELECT u FROM Usuario u where u.phone = ?1")
	Optional<Usuario> findUsuarioByPhone(String telefono);
	
	@Query("SELECT MONTH(date) FROM Meeting m WHERE m.meetingCreator.id=?1 AND YEAR(m.date)=?2")
	List<Integer> findMeetingByMonth(Integer id,Integer year);
	
	@Query("SELECT MONTH(startDate) FROM Championship c WHERE c.user.id=?1 AND YEAR(c.startDate)=?2")
	List<Integer> findChampionshipByMonth(Integer id,Integer year);
}
