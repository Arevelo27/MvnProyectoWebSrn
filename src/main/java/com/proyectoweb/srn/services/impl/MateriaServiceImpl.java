/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.facade.SrnTblMateriaFacade;
import com.proyectoweb.srn.modelo.SrnTblMateria;
import com.proyectoweb.srn.services.MateriaService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(UsuarioServiceImpl.NAME_SERVICE)
public class MateriaServiceImpl extends GenericServiceImpl<SrnTblMateria, Integer> implements MateriaService {

    static final String NAME_SERVICE = "materiaService";

    @Inject
    private SrnTblMateriaFacade materiaFacade;

    @Override
    public GenericFacade<SrnTblMateria, Integer> getFacade() {
        return materiaFacade;
    }

    @Override
    public List<SrnTblMateria> findByCriterio(String criterio) {
        return materiaFacade.findByCriterio(criterio);
    }

}
