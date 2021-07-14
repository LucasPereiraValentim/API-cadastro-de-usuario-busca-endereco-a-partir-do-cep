package com.desafioapi.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
	public ResponseEntity<Page<Usuario>> getListaUsuarios(@PageableDefault(page = 0, size = 5, sort = "nome", direction = Direction.ASC) Pageable pageable){
		
		Page<Usuario> listaUsuario = usuarioRepository.findAll(pageable);
		
		if (listaUsuario.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<Page<Usuario>>(listaUsuario, HttpStatus.OK);
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
	
	@PutMapping(value = "/")
	public ResponseEntity<?> atualizarRegistro(@RequestBody Usuario usuario){
		
		if (usuario.getCpf() != null || !usuario.getCpf().isEmpty()) {
			Usuario usuarioRetorno = usuarioService.atualizar(usuario);
			return new ResponseEntity<String>("Usuário Atualizado com sucesso!", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Erro ao atualizar", HttpStatus.BAD_REQUEST);
		}
			
	}
	
	@GetMapping(value = "/busca/{cpf}")
	public ResponseEntity<Usuario> getUsuario(@PathVariable String cpf){
		
		if (cpf != null) {
			Usuario usuarioPesquisado = usuarioRepository.findById(cpf).get();
			return new ResponseEntity<Usuario>(usuarioPesquisado, HttpStatus.OK);
		} else {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
	}

}
