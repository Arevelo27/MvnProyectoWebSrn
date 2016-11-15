/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.facade.SrnTblMateriaUsuarioFacade;
import com.proyectoweb.srn.modelo.SrnTblMateriaUsuario;
import com.proyectoweb.srn.services.MateriaUsuarioService;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(RolServiceImpl.NAME_SERVICE)
public class MateriaUsuarioServiceImpl extends GenericServiceImpl<SrnTblMateriaUsuario, Integer> implements MateriaUsuarioService {

    static final String NAME_SERVICE = "MateriaUsuarioService";

    @Inject
    private SrnTblMateriaUsuarioFacade materiaUsuarioFacade;

    @Override
    public GenericFacade<SrnTblMateriaUsuario, Integer> getFacade() {
        return materiaUsuarioFacade;
    }

    @Override
    public SrnTblMateriaUsuario findMateriaUser(Integer materia, Integer idDoc, Integer idEst) throws Exception {
        return materiaUsuarioFacade.findMateriaUser(materia, idDoc, idEst);
    }
    
    
}
