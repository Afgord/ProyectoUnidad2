/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectounidad2;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import com.mycompany.proyectounidad2.dominio.Match;
import com.mycompany.proyectounidad2.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;

/**
 *
 * @author Afgord
 */
public class ProyectoUnidad2 {

    public static void main(String[] args) {

        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();

            Estudiante estudiante1 = new Estudiante(
                    "Luis",
                    "Perez",
                    "Lopez",
                    "luis@potros.itson.edu.mx",
                    "123456",
                    "Ingenieria en Software",
                    "fotos/luis.jpg",
                    "Le gusta programar"
            );

            Estudiante estudiante2 = new Estudiante(
                    "Ana",
                    "Garcia",
                    "Torres",
                    "ana2@potros.itson.edu.mx",
                    "abcdef",
                    "Ingenieria en Software",
                    "fotos/ana2.jpg",
                    "Le gusta el ajedrez"
            );

            em.getTransaction().begin();

            em.persist(estudiante1);
            em.persist(estudiante2);

            Match match = new Match(LocalDate.now(), estudiante1, estudiante2);
            em.persist(match);

            em.getTransaction().commit();

            System.out.println("Match guardado correctamente con id: " + match.getId());

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }
}
