package com.vincere.repository;

import com.vincere.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // método para login futuramente
    Optional<Usuario> findByEmail(String email);
}
