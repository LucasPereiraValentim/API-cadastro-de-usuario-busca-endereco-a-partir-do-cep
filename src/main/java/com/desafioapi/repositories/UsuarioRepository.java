package com.desafioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafioapi.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{
	
	@Query(value = "SELECT CASE WHEN COUNT(u) > 0 THEN true ELSE false END FROM Usuario u WHERE u.cpf = :cpf")
	boolean verificarCpf(@Param("cpf") String cpf);
}
