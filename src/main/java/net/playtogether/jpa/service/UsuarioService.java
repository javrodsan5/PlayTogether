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
	
	@Transactional
	public void saveUsuario(Usuario usuario) throws DataAccessException {
		usuarioRepository.save(usuario);
		userService.saveUser(usuario.getUser());
		authoritiesService.saveAuthorities(usuario.getUser().getUsername(), "usuario");
		
	}
	
}
