/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services;

import com.proyectoweb.srn.modelo.SrnTblNota;
import java.util.List;

/**
 *
 * @author TSI
 */
public interface NotaService extends GenericService<SrnTblNota, Integer> {

    Integer findMaxId() throws Exception;

    /**
     *
     * @return @throws Exception
     */
    List<SrnTblNota> buscarTodos() throws Exception;

    List<SrnTblNota> buscarNotasAsignadasUsuario(Integer userDoc, Integer userEst) throws Exception;

    SrnTblNota findMateriaUser(long codigo) throws Exception;
}
