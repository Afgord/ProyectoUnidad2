/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.mycompany.proyectounidad2.persistencia;

import com.mycompany.proyectounidad2.dominio.Hobby;
import java.util.List;

/**
 *
 * @author Afgord
 */
public interface IHobbyDAO {

    Hobby guardar(Hobby hobby);

    Hobby actualizar(Hobby hobby);

    Hobby buscarPorId(Long id);

    Hobby buscarPorNombre(String nombre);

    List<Hobby> obtenerTodos();

}
