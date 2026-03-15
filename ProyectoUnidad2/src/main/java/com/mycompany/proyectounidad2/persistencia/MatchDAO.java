/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounidad2.persistencia;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import com.mycompany.proyectounidad2.dominio.Match;
import com.mycompany.proyectounidad2.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author Afgord
 */
public class MatchDAO implements IMatchDAO {

    @Override
    public Match guardar(Match match) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(match);
            em.getTransaction().commit();
            return match;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Match buscarMatchEntre(Estudiante estudiante1, Estudiante estudiante2) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            String jpql = """
                    SELECT m
                    FROM Match m
                    WHERE m.estudiante1 = :estudiante1
                      AND m.estudiante2 = :estudiante2
                    """;

            TypedQuery<Match> query = em.createQuery(jpql, Match.class);
            query.setParameter("estudiante1", estudiante1);
            query.setParameter("estudiante2", estudiante2);

            return query.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }

}
