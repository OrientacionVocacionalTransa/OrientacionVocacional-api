package com.example.orientacionvocacionalapi.Controller;

import com.example.orientacionvocacionalapi.dto.UserDTO;
import com.example.orientacionvocacionalapi.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserService usuarioService;


    @PostMapping("/register")
    public ResponseEntity<?> registrarUsuario(@Validated @RequestBody UserDTO usuarioDTO) {
        try {
            usuarioService.registrarUsuario(usuarioDTO);
            return ResponseEntity.ok().body("{\"message\": \"Usuario registrado con éxito.\"}");
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("{\"error\": \"Error al registrar el usuario.\"}");
        }
    }
}
