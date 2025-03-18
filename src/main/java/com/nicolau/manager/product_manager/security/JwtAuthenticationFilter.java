package com.nicolau.manager.product_manager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authHeader); // Log do cabeçalho

        String username = null;
        String jwt = null;

        // Tratamento do cabeçalho Authorization
        if (authHeader != null) {
            if (authHeader.startsWith("Bearer Bearer ")) {
                jwt = authHeader.substring(14); // Remove "Bearer Bearer "
            } else if (authHeader.startsWith("Bearer ")) {
                jwt = authHeader.substring(7); // Remove "Bearer "
            }
            System.out.println("JWT Extraído: " + jwt); // Log do token JWT
        }

        // Extração do username do token JWT
        if (jwt != null) {
            try {
                username = jwtService.extractUsername(jwt);
                System.out.println("Username Extraído: " + username); // Log do usuário extraído
            } catch (Exception e) {
                System.out.println("Erro ao extrair username do token JWT: " + e.getMessage());
            }
        }

        // Validação do token e configuração do contexto de segurança
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            if (jwtService.isTokenValid(jwt, userDetails.getUsername())) {
                var authToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
                System.out.println("Autenticação bem-sucedida! Usuário: " + username); // Log de sucesso
            } else {
                System.out.println("Token JWT inválido!"); // Log de falha
            }
        } else {
            System.out.println("Autenticação não realizada ou não necessária."); // Log para requisições anônimas
        }

        filterChain.doFilter(request, response);
    }
}
