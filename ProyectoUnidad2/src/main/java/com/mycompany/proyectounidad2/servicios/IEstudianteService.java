/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectounidad2.servicios;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import java.util.List;

/**
 *
 * @author Afgord
 */
public interface IEstudianteService {

    Estudiante registrarEstudiante(Estudiante estudiante);

    Estudiante buscarPorCorreo(String correoInst);

    Estudiante iniciarSesion(String correoInst, String password);

    Estudiante agregarHobby(Long idEstudiante, Long idHobby);

    Estudiante buscarPorId(Long id);

    Estudiante buscarPorIdConHobbies(Long id);

    List<Estudiante> buscarConHobbiesEnComun(Long idEstudiante);

    List<Estudiante> explorarPerfiles(Long idEstudiante);

}
