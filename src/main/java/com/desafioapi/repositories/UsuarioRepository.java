package com.desafioapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafioapi.models.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String>{

}
