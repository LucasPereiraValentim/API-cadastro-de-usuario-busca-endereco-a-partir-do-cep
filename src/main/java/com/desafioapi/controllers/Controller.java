package com.desafioapi.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioapi.models.Usuario;
import com.desafioapi.repositories.UsuarioRepository;
import com.desafioapi.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class Controller {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Usuario usuario){
		
		if (!usuarioRepository.verificarCpf(usuario.getCpf())) {
			Usuario usuarioSalvo = (Usuario) usuarioService.salvar(usuario);
			return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
		} else {
			String retorno = "Este CPF já está cadastrado no nosso sistema!";
			return new ResponseEntity<String>(retorno, HttpStatus.OK);
		}	
	}
	
	@GetMapping(value = "/")
	public ResponseEntity<List<Usuario>> getLista(){
		
		List<Usuario> listaUsuario = usuarioRepository.findAll();
		
		if (listaUsuario.isEmpty()) {
			return new ResponseEntity(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Usuario>>(listaUsuario, HttpStatus.OK);
		}
		
	}
	
	
	@DeleteMapping(value = "/{cpf}")
	public ResponseEntity<String> excluirRegistro(@PathVariable String cpf){
		
		if (cpf != null) {
			usuarioRepository.deleteById(cpf);
			return new ResponseEntity<String>("Registro excluído com sucesso", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Falha ao excluir", HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping(value = "/{cpf}")
	public ResponseEntity<String> atualizarRegistro(@PathVariable String cpf){
		return null;
	}

}
