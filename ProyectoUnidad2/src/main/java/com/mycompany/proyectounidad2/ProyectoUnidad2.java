/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectounidad2;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import com.mycompany.proyectounidad2.dominio.TipoReaccion;
import com.mycompany.proyectounidad2.servicios.IReaccionService;
import com.mycompany.proyectounidad2.servicios.ReaccionService;
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
                    "ana3@potros.itson.edu.mx",
                    "abcdef",
                    "Ingenieria en Software",
                    "fotos/ana3.jpg",
                    "Le gusta el ajedrez"
            );

            em.getTransaction().begin();
            em.persist(estudiante1);
            em.persist(estudiante2);
            em.getTransaction().commit();

            IReaccionService reaccionService = new ReaccionService();

            reaccionService.registrarReaccion(estudiante1, estudiante2, TipoReaccion.LIKE);
            reaccionService.registrarReaccion(estudiante2, estudiante1, TipoReaccion.LIKE);

            System.out.println("Prueba de matching completada correctamente.");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (em != null && em.isOpen()) {
                em.close();
            }
            JpaUtil.close();
        }

    }
}
