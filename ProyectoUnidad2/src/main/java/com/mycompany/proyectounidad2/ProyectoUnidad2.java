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

            System.out.println("Estudiantes guardados correctamente.");
            System.out.println("ID estudiante1: " + estudiante1.getId());
            System.out.println("ID estudiante2: " + estudiante2.getId());

            IReaccionService reaccionService = new ReaccionService();

            System.out.println("\n--- PRUEBA 1: LIKE de Luis hacia Ana ---");
            reaccionService.registrarReaccion(estudiante1, estudiante2, TipoReaccion.LIKE);
            System.out.println("Reacción 1 registrada correctamente.");

            System.out.println("\n--- PRUEBA 2: LIKE de Ana hacia Luis ---");
            reaccionService.registrarReaccion(estudiante2, estudiante1, TipoReaccion.LIKE);
            System.out.println("Reacción 2 registrada correctamente.");
            System.out.println("Si todo salió bien, debió generarse un MATCH.");

            System.out.println("\n--- PRUEBA 3: actualizar reacción existente ---");
            reaccionService.registrarReaccion(estudiante1, estudiante2, TipoReaccion.NO_INTERESA);
            System.out.println("La reacción de Luis hacia Ana se actualizó a NO_INTERESA.");

            System.out.println("\n--- PRUEBA 4: volver a actualizar reacción existente ---");
            reaccionService.registrarReaccion(estudiante1, estudiante2, TipoReaccion.LIKE);
            System.out.println("La reacción de Luis hacia Ana se actualizó nuevamente a LIKE.");

            System.out.println("\n--- PRUEBA 5: validar auto-reacción ---");
            try {
                reaccionService.registrarReaccion(estudiante1, estudiante1, TipoReaccion.LIKE);
            } catch (IllegalArgumentException e) {
                System.out.println("Validación correcta: " + e.getMessage());
            }

            System.out.println("\n--- PRUEBA EXTRA: intentar duplicar reaccion ---");

            try {
                reaccionService.registrarReaccion(estudiante1, estudiante2, TipoReaccion.LIKE);
                reaccionService.registrarReaccion(estudiante1, estudiante2, TipoReaccion.LIKE);
            } catch (Exception e) {
                System.out.println("Restricción protegió duplicado: " + e.getMessage());
            }

            System.out.println("\nTodas las pruebas del servicio terminaron.");

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
