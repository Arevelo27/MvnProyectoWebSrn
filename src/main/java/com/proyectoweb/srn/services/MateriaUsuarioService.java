/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services;

import com.proyectoweb.srn.modelo.SrnTblMateriaUsuario;

/**
 *
 * @author TSI
 */
public interface MateriaUsuarioService extends GenericService<SrnTblMateriaUsuario, Integer> {

    SrnTblMateriaUsuario findMateriaUser(Integer materia, Integer idDoc, Integer idEst) throws Exception;
}
