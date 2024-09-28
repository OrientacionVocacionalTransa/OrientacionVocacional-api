package com.example.orientacionvocacionalapi.Controller;

import com.example.orientacionvocacionalapi.dto.UserDTO;
import com.example.orientacionvocacionalapi.model.entity.User;
import com.example.orientacionvocacionalapi.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


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
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestParam String email, @RequestParam String password) {
        Map<String, String> response = new HashMap<>();
        if (usuarioService.login(email, password)) {
            response.put("message", "Login exitoso");
            return ResponseEntity.ok(response);
        } else {
            response.put("message", "Credenciales incorrectas");
            return ResponseEntity.status(401).body(response);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            usuarioService.updateUser(id, userDTO);
            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al actualizar el usuario: " + e.getMessage());
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            usuarioService.deleteUser(id);
            return ResponseEntity.ok("Usuario eliminado correctamente");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al eliminar el usuario: " + e.getMessage());
        }
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        try {
            User user = usuarioService.getUserById(id);
            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al obtener el usuario: " + e.getMessage());
        }
    }

}
