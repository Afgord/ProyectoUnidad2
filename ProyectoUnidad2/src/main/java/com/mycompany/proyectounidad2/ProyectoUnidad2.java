/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectounidad2;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import com.mycompany.proyectounidad2.dominio.Reaccion;
import com.mycompany.proyectounidad2.dominio.TipoReaccion;
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

            Estudiante emisor = new Estudiante(
                    "Christian",
                    "Martinez",
                    "Lopez",
                    "christian4@potros.itson.edu.mx",
                    "123456",
                    "Ingenieria en Software",
                    "fotos/christian4.jpg",
                    "Le gusta programar"
            );

            Estudiante receptor = new Estudiante(
                    "Ana",
                    "Garcia",
                    "Torres",
                    "ana@potros.itson.edu.mx",
                    "abcdef",
                    "Ingenieria en Software",
                    "fotos/ana.jpg",
                    "Le gusta el ajedrez"
            );

            Reaccion reaccion = new Reaccion(
                    TipoReaccion.LIKE,
                    LocalDate.now(),
                    emisor,
                    receptor
            );

            em.getTransaction().begin();

            em.persist(emisor);
            em.persist(receptor);
            em.persist(reaccion);

            em.getTransaction().commit();

            System.out.println("Reacción guardada correctamente con id: " + reaccion.getId());

        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            System.out.println("Error al guardar reacción: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
        }

    }
}
