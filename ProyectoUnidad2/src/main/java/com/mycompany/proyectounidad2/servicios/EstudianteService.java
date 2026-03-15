/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounidad2.servicios;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import com.mycompany.proyectounidad2.dominio.Hobby;
import com.mycompany.proyectounidad2.persistencia.EstudianteDAO;
import com.mycompany.proyectounidad2.persistencia.HobbyDAO;
import com.mycompany.proyectounidad2.persistencia.IEstudianteDAO;
import com.mycompany.proyectounidad2.persistencia.IHobbyDAO;
import com.mycompany.proyectounidad2.utils.JpaUtil;
import com.mycompany.proyectounidad2.utils.PasswordUtil;
import jakarta.persistence.EntityManager;

/**
 *
 * @author Afgord
 */
public class EstudianteService implements IEstudianteService {

    @Override
    public Estudiante registrarEstudiante(Estudiante estudiante) {
        validarDatosEstudiante(estudiante);

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            IEstudianteDAO estudianteDAO = new EstudianteDAO(em);

            Estudiante existente = estudianteDAO.buscarPorCorreo(estudiante.getCorreoInst());

            if (existente != null) {
                throw new IllegalArgumentException("Ya existe un estudiante registrado con ese correo institucional.");
            }

            String hash = PasswordUtil.hashPassword(estudiante.getPassword());
            estudiante.setPassword(hash);

            Estudiante guardado = estudianteDAO.guardar(estudiante);

            em.getTransaction().commit();
            return guardado;

        } catch (IllegalArgumentException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al registrar el estudiante.", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Estudiante buscarPorCorreo(String correoInst) {
        if (correoInst == null || correoInst.isBlank()) {
            throw new IllegalArgumentException("El correo institucional no puede ser nulo o vacío.");
        }

        EntityManager em = JpaUtil.getEntityManager();

        try {
            IEstudianteDAO estudianteDAO = new EstudianteDAO(em);
            return estudianteDAO.buscarPorCorreo(correoInst);
        } finally {
            em.close();
        }
    }

    private void validarDatosEstudiante(Estudiante estudiante) {
        if (estudiante == null) {
            throw new IllegalArgumentException("El estudiante no puede ser nulo.");
        }

        if (estudiante.getNombre() == null || estudiante.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede ser nulo o vacío.");
        }

        if (estudiante.getApPat() == null || estudiante.getApPat().isBlank()) {
            throw new IllegalArgumentException("El apellido paterno no puede ser nulo o vacío.");
        }

        if (estudiante.getApMat() == null || estudiante.getApMat().isBlank()) {
            throw new IllegalArgumentException("El apellido materno no puede ser nulo o vacío.");
        }

        if (estudiante.getCorreoInst() == null || estudiante.getCorreoInst().isBlank()) {
            throw new IllegalArgumentException("El correo institucional no puede ser nulo o vacío.");
        }

        if (estudiante.getPassword() == null || estudiante.getPassword().isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
        }

        if (estudiante.getCarrera() == null || estudiante.getCarrera().isBlank()) {
            throw new IllegalArgumentException("La carrera no puede ser nula o vacía.");
        }
    }

    @Override
    public Estudiante iniciarSesion(String correoInst, String password) {
        if (correoInst == null || correoInst.isBlank()) {
            throw new IllegalArgumentException("El correo institucional no puede ser nulo o vacío.");
        }

        if (password == null || password.isBlank()) {
            throw new IllegalArgumentException("La contraseña no puede ser nula o vacía.");
        }

        EntityManager em = JpaUtil.getEntityManager();

        try {
            IEstudianteDAO estudianteDAO = new EstudianteDAO(em);

            Estudiante estudiante = estudianteDAO.buscarPorCorreo(correoInst);

            if (estudiante == null) {
                throw new IllegalArgumentException("No existe un estudiante con ese correo institucional.");
            }

            boolean passwordCorrecto = PasswordUtil.verificarPassword(password, estudiante.getPassword());

            if (!passwordCorrecto) {
                throw new IllegalArgumentException("Contraseña incorrecta.");
            }

            return estudiante;

        } finally {
            em.close();
        }
    }

    @Override
    public Estudiante agregarHobby(Long idEstudiante, Long idHobby) {
        if (idEstudiante == null) {
            throw new IllegalArgumentException("El id del estudiante no puede ser nulo.");
        }

        if (idHobby == null) {
            throw new IllegalArgumentException("El id del hobby no puede ser nulo.");
        }

        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();

            IEstudianteDAO estudianteDAO = new EstudianteDAO(em);
            IHobbyDAO hobbyDAO = new HobbyDAO(em);

            Estudiante estudiante = estudianteDAO.buscarPorId(idEstudiante);
            if (estudiante == null) {
                throw new IllegalArgumentException("No existe un estudiante con ese id.");
            }

            Hobby hobby = hobbyDAO.buscarPorId(idHobby);
            if (hobby == null) {
                throw new IllegalArgumentException("No existe un hobby con ese id.");
            }

            estudiante.getHobbies().add(hobby);

            Estudiante actualizado = estudianteDAO.actualizar(estudiante);

            em.getTransaction().commit();
            return actualizado;

        } catch (IllegalArgumentException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error al agregar hobby al estudiante.", e);
        } finally {
            em.close();
        }
    }

    @Override
    public Estudiante buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El id del estudiante no puede ser nulo.");
        }

        EntityManager em = JpaUtil.getEntityManager();

        try {
            IEstudianteDAO estudianteDAO = new EstudianteDAO(em);
            return estudianteDAO.buscarPorId(id);
        } finally {
            em.close();
        }
    }

    @Override
    public Estudiante buscarPorIdConHobbies(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El id no puede ser nulo.");
        }

        EntityManager em = JpaUtil.getEntityManager();

        try {

            IEstudianteDAO estudianteDAO = new EstudianteDAO(em);

            Estudiante estudiante = estudianteDAO.buscarPorIdConHobbies(id);

            if (estudiante == null) {
                throw new IllegalArgumentException("No existe un estudiante con ese id.");
            }

            return estudiante;

        } finally {
            em.close();
        }
    }

}
