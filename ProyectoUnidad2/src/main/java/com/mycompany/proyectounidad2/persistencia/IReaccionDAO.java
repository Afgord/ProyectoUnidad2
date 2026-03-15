/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectounidad2.persistencia;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import com.mycompany.proyectounidad2.dominio.Reaccion;

/**
 *
 * @author Afgord
 */
public interface IReaccionDAO {

    Reaccion guardar(Reaccion reaccion);

    Reaccion actualizar(Reaccion reaccion);

    Reaccion buscarPorEmisorReceptor(Estudiante emisor, Estudiante receptor);

}
