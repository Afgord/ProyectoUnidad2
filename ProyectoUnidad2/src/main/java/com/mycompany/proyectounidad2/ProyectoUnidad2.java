/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectounidad2;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import com.mycompany.proyectounidad2.dominio.Hobby;
import com.mycompany.proyectounidad2.utils.JpaUtil;
import jakarta.persistence.EntityManager;

/**
 *
 * @author Afgord
 */
public class ProyectoUnidad2 {

    public static void main(String[] args) {

        EntityManager em = null;

        try {
            em = JpaUtil.getEntityManager();

            Hobby hobby = new Hobby("Ajedrez", "Juego de estrategia");
            Estudiante estudiante = new Estudiante(
                    "Christian",
                    "Martinez",
                    "Ejemplo",
                    "christian2@potros.itson.edu.mx",
                    "123456",
                    "Ingenieria en Software",
                    "fotos/christian2.jpg",
                    "Le gusta aprender y practicar."
            );

            estudiante.getHobbies().add(hobby);

            em.getTransaction().begin();
            em.persist(hobby);
            em.persist(estudiante);
            em.getTransaction().commit();

            System.out.println("Relación estudiante-hobby guardada correctamente.");

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
