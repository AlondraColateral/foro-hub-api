package com.forohub.foro_hub.api.config;

import com.forohub.foro_hub.api.domain.usuario.repository.UsuarioRepository;
import com.forohub.foro_hub.api.service.TokenService;
import com.auth0.jwt.exceptions.JWTVerificationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var authHeader = request.getHeader("Authorization");

        if (authHeader != null) {
            try {
                var token = authHeader.replace("Bearer ", "");
                var nombreUsuario = tokenService.getSubject(token);
                if (nombreUsuario != null) {
                    var usuario = usuarioRepository.findByLogin(nombreUsuario);
                    var authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            } catch (JWTVerificationException ex) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // 401 Unauthorized
                response.getWriter().write("{\"error\": \"Token inválido o caducado.\"}");
                return; // Detiene la cadena de filtros
            }
        }
        filterChain.doFilter(request, response);
    }
}