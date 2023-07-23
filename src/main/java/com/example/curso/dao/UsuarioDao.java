package com.example.curso.dao;

import com.example.curso.models.Usuario;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();
    Usuario getUsuario(Long id);

    Long eliminar(Long id);
    Usuario agregar( String nombre,String apellido,String email,String telefono,String password);

    int actualizar(Long id,String nombre,String apellido,
                   String email,String telefono,
                   String password);
}
