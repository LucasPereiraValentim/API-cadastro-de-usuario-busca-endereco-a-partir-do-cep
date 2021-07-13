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
			// Classe para realizar consumo de api externa
			RestTemplate restTemplate = new RestTemplate();
			
			//Realizando o consumo de API externa
			Endereco endereco = restTemplate.getForObject("https://viacep.com.br/ws/"+usuario.getEndereco().getCep()+"/json/", Endereco.class);
			
			usuario.setEndereco(endereco);
			
			usuario.getEndereco().setUsuario(usuario);
			
			Usuario usuarioSalvo = usuarioRepository.save(usuario);
			
			return usuarioSalvo;
		
	}
	
	public Usuario atualizar(Usuario usuario) {
		
		Usuario usuarioAtualizado = new Usuario();
		
		usuarioAtualizado.setNome(usuario.getNome());
		usuarioAtualizado.setEmail(usuario.getEmail());
		usuarioAtualizado.setCpf(usuario.getCpf());
		usuarioAtualizado.setDataNascimento(usuario.getDataNascimento());
		
		
		RestTemplate restTemplate = new RestTemplate();
		
		Endereco endereco = restTemplate.getForObject("https://viacep.com.br/ws/"+usuario.getEndereco().getCep()+"/json/", Endereco.class);
		
		usuarioAtualizado.setEndereco(endereco);
		
		usuarioAtualizado.getEndereco().setUsuario(usuario);
		
		
		usuarioRepository.save(usuarioAtualizado);
		
		return usuarioAtualizado;
		
	}
	
}
