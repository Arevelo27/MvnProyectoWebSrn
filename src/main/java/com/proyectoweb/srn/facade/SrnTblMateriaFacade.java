/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.facade;

import com.proyectoweb.srn.modelo.SrnTblMateria;
import java.util.List;

/**
 *
 * @author TSI
 */
public interface SrnTblMateriaFacade extends GenericFacade<SrnTblMateria, Integer> {

    List<SrnTblMateria> findByCriterio(String criterio);
}
