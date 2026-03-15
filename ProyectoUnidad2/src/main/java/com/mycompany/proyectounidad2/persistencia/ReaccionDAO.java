/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounidad2.persistencia;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import com.mycompany.proyectounidad2.dominio.Reaccion;
import com.mycompany.proyectounidad2.dominio.TipoReaccion;
import com.mycompany.proyectounidad2.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 *
 * @author Afgord
 */
public class ReaccionDAO implements IReaccionDAO {

    @Override
    public Reaccion guardar(Reaccion reaccion) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            em.getTransaction().begin();
            em.persist(reaccion);
            em.getTransaction().commit();
            return reaccion;
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
    public Reaccion buscarReaccion(Estudiante emisor, Estudiante receptor, TipoReaccion tipo) {
        EntityManager em = JpaUtil.getEntityManager();

        try {
            String jpql = """
                    SELECT r
                    FROM Reaccion r
                    WHERE r.emisor = :emisor
                      AND r.receptor = :receptor
                      AND r.tipo = :tipo
                    """;

            TypedQuery<Reaccion> query = em.createQuery(jpql, Reaccion.class);
            query.setParameter("emisor", emisor);
            query.setParameter("receptor", receptor);
            query.setParameter("tipo", tipo);

            return query.getResultStream().findFirst().orElse(null);
        } finally {
            em.close();
        }
    }

}
