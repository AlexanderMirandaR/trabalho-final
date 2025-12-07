package com.app.controllers;

import com.app.auth.JwtUtil;
import com.app.dto.LoginRequest;
import com.app.dto.LoginResponse;
import com.app.models.Usuario;
import com.app.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/auth")
public class AuthController {
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private JwtUtil jwtUtil;
    
    @PostMapping("/register")
    public ResponseEntity<LoginResponse> register(@RequestBody LoginRequest request) {
        if (usuarioRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        
        Usuario usuario = new Usuario();
        usuario.setEmail(request.getEmail());
        usuario.setSenha(request.getSenha());
        usuario.setNome(request.getEmail().split("@")[0]);
        
        Usuario salvo = usuarioRepository.save(usuario);
        String token = jwtUtil.generateToken(salvo.getEmail());
        
        return ResponseEntity.ok(new LoginResponse(token, salvo.getEmail(), salvo.getNome()));
    }
    
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        
        if (!usuario.getSenha().equals(request.getSenha())) {
            return ResponseEntity.badRequest().build();
        }
        
        String token = jwtUtil.generateToken(usuario.getEmail());
        return ResponseEntity.ok(new LoginResponse(token, usuario.getEmail(), usuario.getNome()));
    }
}
