package com.forohub.foro_hub.api.controller;

import com.forohub.foro_hub.api.domain.usuario.DatosRegistroUsuario;
import com.forohub.foro_hub.api.domain.usuario.Usuario;
import com.forohub.foro_hub.api.domain.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/register")
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<Void> registrarUsuario(@RequestBody @Valid DatosRegistroUsuario datos) {
        String hashedPassword = passwordEncoder.encode(datos.password());
        Usuario nuevoUsuario = new Usuario(null, datos.login(), hashedPassword);
        usuarioRepository.save(nuevoUsuario);
        return ResponseEntity.ok().build();
    }
}
