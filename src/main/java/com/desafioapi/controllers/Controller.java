package com.desafioapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.desafioapi.models.Usuario;
import com.desafioapi.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class Controller {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario){
		
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		
		
		
		return new ResponseEntity<Usuario>(usuarioSalvo, HttpStatus.OK);
	}

}
