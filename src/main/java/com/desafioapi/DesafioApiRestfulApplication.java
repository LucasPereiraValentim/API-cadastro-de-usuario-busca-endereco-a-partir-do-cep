package com.desafioapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;

import com.desafioapi.controllers.Controller;
import com.desafioapi.repositories.UsuarioRepository;
import com.desafioapi.service.UsuarioService;

@SpringBootApplication
@EntityScan(basePackages = {"com.desafioapi.models"})
@ComponentScan(basePackageClasses = {Controller.class, UsuarioService.class, UsuarioRepository.class})
@EnableJpaRepositories(basePackages = {"com.desafioapi.repositories"})
@RestController
@EnableAutoConfiguration
public class DesafioApiRestfulApplication {
	public static void main(String[] args) {
		SpringApplication.run(DesafioApiRestfulApplication.class, args);
	}

}
