/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.proyectounidad2;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import com.mycompany.proyectounidad2.dominio.Hobby;
import com.mycompany.proyectounidad2.dominio.TipoReaccion;
import com.mycompany.proyectounidad2.servicios.EstudianteService;
import com.mycompany.proyectounidad2.servicios.HobbyService;
import com.mycompany.proyectounidad2.servicios.IEstudianteService;
import com.mycompany.proyectounidad2.servicios.IHobbyService;
import com.mycompany.proyectounidad2.servicios.IReaccionService;
import com.mycompany.proyectounidad2.servicios.ReaccionService;
import com.mycompany.proyectounidad2.utils.JpaUtil;
import java.util.List;

/**
 *
 * @author Afgord
 */
public class ProyectoUnidad2 {

    public static void main(String[] args) {

        try {
            IEstudianteService estudianteService = new EstudianteService();
            IHobbyService hobbyService = new HobbyService();
            IReaccionService reaccionService = new ReaccionService();

            // =========================================================
            // PRUEBA 1: REGISTRO DE ESTUDIANTES
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 1: REGISTRO DE ESTUDIANTES");
            System.out.println("=================================================");

            Estudiante estudiante1 = new Estudiante(
                    "Luis",
                    "Perez",
                    "Lopez",
                    "luis@potros.itson.edu.mx",
                    "H3lL0o",
                    "Ingenieria en Software",
                    "fotos/luis.jpg",
                    "Le gusta programar"
            );

            Estudiante estudiante2 = new Estudiante(
                    "Ana",
                    "Garcia",
                    "Torres",
                    "ana3@potros.itson.edu.mx",
                    "1j23juH",
                    "Ingenieria en Software",
                    "fotos/ana3.jpg",
                    "Le gusta el ajedrez"
            );

            Estudiante estudiante3 = new Estudiante(
                    "Carlos",
                    "Mendoza",
                    "Ruiz",
                    "carlos@potros.itson.edu.mx",
                    "Qi3T8n",
                    "Ingenieria en Software",
                    "fotos/carlos.jpg",
                    "Le gusta el anime y los videojuegos"
            );

            estudiante1 = estudianteService.registrarEstudiante(estudiante1);
            estudiante2 = estudianteService.registrarEstudiante(estudiante2);
            estudiante3 = estudianteService.registrarEstudiante(estudiante3);

            System.out.println("Estudiantes registrados correctamente.");
            System.out.println("ID estudiante1: " + estudiante1.getId());
            System.out.println("ID estudiante2: " + estudiante2.getId());
            System.out.println("ID estudiante3: " + estudiante3.getId());

            // =========================================================
            // PRUEBA 2: BUSCAR ESTUDIANTE POR CORREO
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 2: BUSCAR ESTUDIANTE POR CORREO");
            System.out.println("=================================================");

            Estudiante encontrado = estudianteService.buscarPorCorreo("luis@potros.itson.edu.mx");
            if (encontrado != null) {
                System.out.println("Estudiante encontrado: "
                        + encontrado.getNombre() + " "
                        + encontrado.getApPat());
            } else {
                System.out.println("No se encontró estudiante.");
            }

            // =========================================================
            // PRUEBA 3: VALIDAR CORREO DUPLICADO
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 3: VALIDAR CORREO DUPLICADO");
            System.out.println("=================================================");

            try {
                Estudiante duplicado = new Estudiante(
                        "Luis2",
                        "Perez2",
                        "Lopez2",
                        "luis@potros.itson.edu.mx",
                        "H3lL0o",
                        "Ingenieria en Software",
                        "fotos/luis2.jpg",
                        "Correo repetido"
                );

                estudianteService.registrarEstudiante(duplicado);
            } catch (IllegalArgumentException e) {
                System.out.println("Validación correcta: " + e.getMessage());
            }

            // =========================================================
            // PRUEBA 4: LOGIN CORRECTO
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 4: LOGIN CORRECTO");
            System.out.println("=================================================");

            Estudiante loginCorrecto = estudianteService.iniciarSesion(
                    "luis@potros.itson.edu.mx",
                    "H3lL0o"
            );
            System.out.println("Inicio de sesión correcto para: " + loginCorrecto.getNombre());

            // =========================================================
            // PRUEBA 5: LOGIN CON CONTRASEÑA INCORRECTA
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 5: LOGIN CON CONTRASEÑA INCORRECTA");
            System.out.println("=================================================");

            try {
                estudianteService.iniciarSesion("luis@potros.itson.edu.mx", "000000");
            } catch (IllegalArgumentException e) {
                System.out.println("Validación correcta: " + e.getMessage());
            }

            // =========================================================
            // PRUEBA 6: LOGIN CON CORREO INEXISTENTE
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 6: LOGIN CON CORREO INEXISTENTE");
            System.out.println("=================================================");

            try {
                estudianteService.iniciarSesion("noexiste@potros.itson.edu.mx", "123456");
            } catch (IllegalArgumentException e) {
                System.out.println("Validación correcta: " + e.getMessage());
            }

            // =========================================================
            // PRUEBA 7: REGISTRO DE HOBBIES
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 7: REGISTRO DE HOBBIES");
            System.out.println("=================================================");

            Hobby hobby1 = hobbyService.registrarHobby(
                    new Hobby("Programación", "Desarrollo de software")
            );
            Hobby hobby2 = hobbyService.registrarHobby(
                    new Hobby("Ajedrez", "Juego de estrategia")
            );
            Hobby hobby3 = hobbyService.registrarHobby(
                    new Hobby("Videojuegos", "Entretenimiento digital")
            );

            System.out.println("Hobbies registrados correctamente.");
            System.out.println("ID hobby1: " + hobby1.getId());
            System.out.println("ID hobby2: " + hobby2.getId());
            System.out.println("ID hobby3: " + hobby3.getId());

            // =========================================================
            // PRUEBA 8: BUSCAR HOBBY POR NOMBRE
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 8: BUSCAR HOBBY POR NOMBRE");
            System.out.println("=================================================");

            Hobby hobbyEncontrado = hobbyService.buscarPorNombre("Ajedrez");
            if (hobbyEncontrado != null) {
                System.out.println("Hobby encontrado: " + hobbyEncontrado.getNombre());
            } else {
                System.out.println("No se encontró hobby.");
            }

            // =========================================================
            // PRUEBA 9: LISTAR HOBBIES
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 9: LISTAR HOBBIES");
            System.out.println("=================================================");

            List<Hobby> hobbies = hobbyService.obtenerTodos();
            for (Hobby h : hobbies) {
                System.out.println(h.getId() + " - " + h.getNombre());
            }

            // =========================================================
            // PRUEBA 10: VALIDAR HOBBY DUPLICADO
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 10: VALIDAR HOBBY DUPLICADO");
            System.out.println("=================================================");

            try {
                hobbyService.registrarHobby(new Hobby("Ajedrez", "Duplicado"));
            } catch (IllegalArgumentException e) {
                System.out.println("Validación correcta: " + e.getMessage());
            }

            // =========================================================
            // PRUEBA 11: ASIGNAR HOBBIES A ESTUDIANTES
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 11: ASIGNAR HOBBIES A ESTUDIANTES");
            System.out.println("=================================================");

            estudianteService.agregarHobby(estudiante1.getId(), hobby1.getId());
            estudianteService.agregarHobby(estudiante1.getId(), hobby3.getId());
            estudianteService.agregarHobby(estudiante2.getId(), hobby2.getId());
            estudianteService.agregarHobby(estudiante2.getId(), hobby3.getId());

            System.out.println("Hobbies asignados correctamente.");

            // =========================================================
            // PRUEBA 12: VALIDAR ESTUDIANTE INEXISTENTE AL ASIGNAR HOBBY
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 12: VALIDAR ESTUDIANTE INEXISTENTE AL ASIGNAR HOBBY");
            System.out.println("=================================================");

            try {
                estudianteService.agregarHobby(999L, hobby1.getId());
            } catch (IllegalArgumentException e) {
                System.out.println("Validación correcta: " + e.getMessage());
            }

            // =========================================================
            // PRUEBA 13: VALIDAR HOBBY INEXISTENTE AL ASIGNAR
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 13: VALIDAR HOBBY INEXISTENTE AL ASIGNAR");
            System.out.println("=================================================");

            try {
                estudianteService.agregarHobby(estudiante1.getId(), 999L);
            } catch (IllegalArgumentException e) {
                System.out.println("Validación correcta: " + e.getMessage());
            }

            // =========================================================
            // PRUEBA 14: REGISTRAR REACCIONES Y GENERAR MATCH
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 14: REGISTRAR REACCIONES Y GENERAR MATCH");
            System.out.println("=================================================");

            reaccionService.registrarReaccion(estudiante1, estudiante2, TipoReaccion.LIKE);
            System.out.println("Reacción 1 registrada correctamente.");

            reaccionService.registrarReaccion(estudiante2, estudiante1, TipoReaccion.LIKE);
            System.out.println("Reacción 2 registrada correctamente.");
            System.out.println("Si todo salió bien, debió generarse un MATCH.");

            // =========================================================
            // PRUEBA 15: ACTUALIZAR REACCIÓN EXISTENTE
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 15: ACTUALIZAR REACCIÓN EXISTENTE");
            System.out.println("=================================================");

            reaccionService.registrarReaccion(estudiante1, estudiante2, TipoReaccion.NO_INTERESA);
            System.out.println("Reacción actualizada correctamente a NO_INTERESA.");

            reaccionService.registrarReaccion(estudiante1, estudiante2, TipoReaccion.LIKE);
            System.out.println("Reacción actualizada nuevamente a LIKE.");

            // =========================================================
            // PRUEBA 16: VALIDAR AUTO-REACCIÓN
            // =========================================================
            System.out.println("\n=================================================");
            System.out.println("PRUEBA 16: VALIDAR AUTO-REACCIÓN");
            System.out.println("=================================================");

            try {
                reaccionService.registrarReaccion(estudiante1, estudiante1, TipoReaccion.LIKE);
            } catch (IllegalArgumentException e) {
                System.out.println("Validación correcta: " + e.getMessage());
            }

            System.out.println("\n=================================================");
            System.out.println("PRUEBA 17: VER HOBBIES DE ESTUDIANTE");
            System.out.println("=================================================");

            Estudiante estudianteConHobbies
                    = estudianteService.buscarPorIdConHobbies(estudiante1.getId());

            System.out.println("Hobbies de " + estudianteConHobbies.getNombre() + ":");

            for (Hobby h : estudianteConHobbies.getHobbies()) {
                System.out.println("- " + h.getNombre());
            }

            System.out.println("\n=================================================");
            System.out.println("PRUEBA 18: BUSCAR ESTUDIANTES CON HOBBIES EN COMUN");
            System.out.println("=================================================");

            List<Estudiante> compatibles = estudianteService.buscarConHobbiesEnComun(estudiante1.getId());

            System.out.println("Estudiantes compatibles con " + estudiante1.getNombre() + ":");
            for (Estudiante e : compatibles) {
                System.out.println("- " + e.getNombre() + " " + e.getApPat());
            }

            System.out.println("\n=================================================");
            System.out.println("PRUEBA 19: EXPLORAR PERFILES");
            System.out.println("=================================================");

            List<Estudiante> perfiles = estudianteService.explorarPerfiles(estudiante1.getId());

            System.out.println("Perfiles disponibles para " + estudiante1.getNombre() + ":");
            for (Estudiante e : perfiles) {
                System.out.println("- " + e.getNombre() + " " + e.getApPat());
            }

            System.out.println("\n=================================================");
            System.out.println("TODAS LAS PRUEBAS FINALIZARON");
            System.out.println("=================================================");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JpaUtil.close();
        }

    }
}
