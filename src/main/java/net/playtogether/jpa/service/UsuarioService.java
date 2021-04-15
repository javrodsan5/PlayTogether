package net.playtogether.jpa.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import net.playtogether.jpa.entity.Usuario;
import net.playtogether.jpa.repository.UsuarioRepository;

@Service
public class UsuarioService {

	UsuarioRepository usuarioRepository;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AuthoritiesService authoritiesService;

	public UsuarioService(UsuarioRepository userRepository){
		this.usuarioRepository=userRepository;
	}
	
	public Usuario usuarioLogueado(String username) {
		Usuario usuario = findByUsername(username);
		return usuario;
	}

	
	@Transactional(readOnly=true)
	public Usuario findUserById(int id){
		return usuarioRepository.findById(id).orElse(null);
	}
	
	public boolean checkCorreoExists(String correo) {
		return usuarioRepository.findUsuarioByCorreo(correo).isPresent();
	}
	public boolean checkUsernameExists(String username) {
		return usuarioRepository.findUsuarioByUsername(username).isPresent();
	}

	public boolean checkPhoneExists(String phone) {
		return usuarioRepository.findUsuarioByPhone(phone).isPresent();
	}

	
	@Transactional
	public void saveUsuario(Usuario usuario) throws DataAccessException {
		usuarioRepository.save(usuario);
		userService.saveUser(usuario.getUser());
		authoritiesService.saveAuthorities(usuario.getUser().getUsername(), "usuario");
		
	}

	@Transactional
	public Usuario findByUsername(String username) {
		return this.usuarioRepository.findUsuarioByUsername(username).orElse(null);
	}
	
	@Transactional(readOnly=true)
	public List<Usuario>findAll(){
		return usuarioRepository.findAll();
	}
	
	@Transactional(readOnly=true)
	public List<Integer>findMeetingByMonth(Integer id, Integer year){
		return usuarioRepository.findMeetingByMonth(id,year);
	}
	
	@Transactional(readOnly=true)
	public List<Integer>findChampionshipByMonth(Integer id, Integer year){
		return usuarioRepository.findChampionshipByMonth(id,year);
	}

	public List<Usuario> findTopUsuarios(){
		return usuarioRepository.findTopUsuarios();
	}
	
	
}
