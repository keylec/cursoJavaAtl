package com.example.curso.dao;

import com.example.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements  UsuarioDao{
    @PersistenceContext
    private EntityManager entityManager;
    @Override
    @Transactional
    public List<Usuario> getUsuarios() {
        String query = "FROM Usuario";
        return entityManager.createQuery(query).getResultList();

    }

    @Override
    public Usuario getUsuario(Long id) {
        String query = "FROM Usuario WHERE id="+ id;
        List<Usuario> usuario = entityManager.createQuery(query).getResultList();
        if(usuario.isEmpty()){
            return null;
        }else{
            return usuario.get(0);
        }
    }

    @Override
    public Long eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class,id);
        if(usuario==null){
            return null;
        }else{
            entityManager.remove(usuario);
            return (long) usuario.getId();
        }
    }

    @Override
    public Usuario agregar(String nombre, String apellido, String email, String telefono, String password) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(1,1024,1,password);
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setPassword(hash);
        entityManager.persist(usuario);
        return usuario;
    }

    @Override
    public int actualizar(Long id,String nombre,String apellido,
                          String email,String telefono,
                          String password) {
        Usuario usuario = entityManager.find(Usuario.class,id);
        if(usuario == null){
            return -1;
        }
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setPassword(password);
        entityManager.merge(usuario);
        return Integer.parseInt(usuario.getId().toString());
    }

}
