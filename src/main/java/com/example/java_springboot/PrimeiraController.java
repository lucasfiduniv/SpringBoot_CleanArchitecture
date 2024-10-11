package com.example.java_springboot;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/primeira")
public class PrimeiraController {
    @GetMapping("/")
    public Usuario retornoPrimeiraController() {
        var user = new Usuario("João", 30);
        return user;
    }
    record Usuario(String nome, int idade) {}
    
}
