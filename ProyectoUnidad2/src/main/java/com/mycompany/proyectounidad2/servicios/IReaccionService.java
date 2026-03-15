/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectounidad2.servicios;

import com.mycompany.proyectounidad2.dominio.Estudiante;
import com.mycompany.proyectounidad2.dominio.Reaccion;
import com.mycompany.proyectounidad2.dominio.TipoReaccion;

/**
 *
 * @author Afgord
 */
public interface IReaccionService {

    Reaccion registrarReaccion(Estudiante emisor, Estudiante receptor, TipoReaccion tipo);

}
