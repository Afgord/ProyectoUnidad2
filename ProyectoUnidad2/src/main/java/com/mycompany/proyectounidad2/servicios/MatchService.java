/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounidad2.servicios;

import com.mycompany.proyectounidad2.dominio.Match;
import com.mycompany.proyectounidad2.persistencia.EstudianteDAO;
import com.mycompany.proyectounidad2.persistencia.IMatchDAO;
import com.mycompany.proyectounidad2.persistencia.MatchDAO;
import com.mycompany.proyectounidad2.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import java.util.List;

/**
 *
 * @author Afgord
 */
public class MatchService implements IMatchService {

    @Override
    public List<Match> obtenerMatchesDeEstudiante(Long idEstudiante) {
        if (idEstudiante == null) {
            throw new IllegalArgumentException("El id del estudiante no puede ser nulo.");
        }

        EntityManager em = JpaUtil.getEntityManager();

        try {
            EstudianteDAO estudianteDAO = new EstudianteDAO(em);
            if (estudianteDAO.buscarPorId(idEstudiante) == null) {
                throw new IllegalArgumentException("No existe un estudiante con ese id.");
            }

            IMatchDAO matchDAO = new MatchDAO(em);
            return matchDAO.buscarMatchesDeEstudiante(idEstudiante);
        } finally {
            em.close();
        }
    }
}
