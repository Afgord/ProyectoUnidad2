/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectounidad2.persistencia;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import com.mycompany.proyectounidad2.dominio.Match;
import java.util.List;

/**
 *
 * @author Afgord
 */
public interface IMatchDAO {

    Match guardar(Match match);

    Match buscarMatchEntre(Estudiante estudiante1, Estudiante estudiante2);

    List<Match> buscarMatchesDeEstudiante(Long idEstudiante);

}
