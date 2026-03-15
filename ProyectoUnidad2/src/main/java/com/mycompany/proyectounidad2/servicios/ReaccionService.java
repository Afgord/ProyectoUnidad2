/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyectounidad2.servicios;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import com.mycompany.proyectounidad2.dominio.Match;
import com.mycompany.proyectounidad2.dominio.Reaccion;
import com.mycompany.proyectounidad2.dominio.TipoReaccion;
import com.mycompany.proyectounidad2.persistencia.IMatchDAO;
import com.mycompany.proyectounidad2.persistencia.IReaccionDAO;
import com.mycompany.proyectounidad2.persistencia.MatchDAO;
import com.mycompany.proyectounidad2.persistencia.ReaccionDAO;
import java.time.LocalDate;

/**
 *
 * @author Afgord
 */
public class ReaccionService implements IReaccionService {

    private final IReaccionDAO reaccionDAO;
    private final IMatchDAO matchDAO;

    public ReaccionService() {
        this.reaccionDAO = new ReaccionDAO();
        this.matchDAO = new MatchDAO();
    }

    @Override
    public Reaccion registrarReaccion(Estudiante emisor, Estudiante receptor, TipoReaccion tipo) {
        if (emisor == null || receptor == null) {
            throw new IllegalArgumentException("Emisor y receptor no pueden ser nulos.");
        }

        if (emisor.getId().equals(receptor.getId())) {
            throw new IllegalArgumentException("Un estudiante no puede reaccionar a sí mismo.");
        }

        Reaccion reaccion = new Reaccion(tipo, LocalDate.now(), emisor, receptor);
        reaccionDAO.guardar(reaccion);

        if (tipo == TipoReaccion.LIKE) {
            Reaccion reaccionInversa = reaccionDAO.buscarReaccion(receptor, emisor, TipoReaccion.LIKE);

            if (reaccionInversa != null) {
                Estudiante estudiante1 = emisor;
                Estudiante estudiante2 = receptor;

                if (estudiante1.getId() > estudiante2.getId()) {
                    estudiante1 = receptor;
                    estudiante2 = emisor;
                }

                Match existente = matchDAO.buscarMatchEntre(estudiante1, estudiante2);

                if (existente == null) {
                    Match match = new Match(LocalDate.now(), estudiante1, estudiante2);
                    matchDAO.guardar(match);
                }
            }
        }

        return reaccion;

    }

}
