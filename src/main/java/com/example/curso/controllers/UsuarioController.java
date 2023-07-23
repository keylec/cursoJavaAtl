package com.example.curso.controllers;

import com.example.curso.dao.UsuarioDao;
import com.example.curso.models.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {
    @Autowired
    private UsuarioDao usuarioDao;
    @RequestMapping("/usuarios")
    public List<Usuario> getUsuarios(){
        return usuarioDao.getUsuarios();
    }

    @RequestMapping("/usuario/{id}")
    public Usuario getUsuario(@PathVariable Long id){
        return usuarioDao.getUsuario(id);
    }
    @RequestMapping(value = "/agregar/{nombre}/{apellido}/{email}/{telefono}/{password}",method = RequestMethod.POST)
    public  Usuario add(
            @PathVariable String nombre,
            @PathVariable String apellido,
            @PathVariable String email,
            @PathVariable String telefono,
            @PathVariable String password
    ) {
        return usuarioDao.agregar(nombre,apellido,email,telefono,password);
    }
//    @RequestMapping(value = "/agregar",method = RequestMethod.POST)
//    public @ResponseBody Usuario add(
//            @RequestParam String nombre,
//            @RequestParam String apellido,
//            @RequestParam String email,
//            @RequestParam String telefono,
//            @RequestParam String password
//    ) {
//      return usuarioDao.agregar(nombre,apellido,email,telefono,password);
//    }
    @RequestMapping("/editar")
    public Usuario editar(){
        Usuario usuario  = new Usuario();
        usuario.setNombre("Lucas");
        usuario.setApellido("moy");
        usuario.setEmail("moy@gmail.com");
        usuario.setTelefono("123124124");
        usuario.setPassword("123");
        return usuario;
    }

    @RequestMapping(value = "/eliminar/{id}",method = RequestMethod.DELETE)
    public Long eliminar(@PathVariable Long id){
        if(usuarioDao.eliminar(id) == null){
            return  -1L;
        }
        return usuarioDao.eliminar(id);
    }

    @RequestMapping("/buscar")
    public Usuario buscar(){
        Usuario usuario  = new Usuario();
        usuario.setNombre("Lucas");
        usuario.setApellido("moy");
        usuario.setEmail("moy@gmail.com");
        usuario.setTelefono("123124124");
        usuario.setPassword("123");
        return usuario;
    }

    @RequestMapping(value = "/actualizar/{id}/{nombre}/{apellido}/{email}/{telefono}/{password}",method = RequestMethod.PUT)
    public int actualizar(@PathVariable Long id,
                          @PathVariable String nombre,
                          @PathVariable String apellido,
                          @PathVariable String email,
                          @PathVariable String telefono,
                          @PathVariable String password){
        return usuarioDao.actualizar(id,nombre,apellido,email,telefono,password);
    }
}
