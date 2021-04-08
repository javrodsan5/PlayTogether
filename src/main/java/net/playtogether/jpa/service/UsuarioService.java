package net.playtogether.jpa.service;

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
	
}
