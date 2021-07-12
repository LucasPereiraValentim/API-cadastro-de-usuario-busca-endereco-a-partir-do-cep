package com.desafioapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.desafioapi.models.Endereco;
import com.desafioapi.models.Usuario;
import com.desafioapi.repositories.UsuarioRepository;

@Service
public class UsuarioService{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario salvar(Usuario usuario) {
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		Endereco endereco = restTemplate.getForObject("https://viacep.com.br/ws/"+usuario.getEndereco().getCep()+"/json/", null)
		
		Usuario usuarioSalvo = usuarioRepository.save(usuario);
		
		return usuarioSalvo;
	}
	
}
