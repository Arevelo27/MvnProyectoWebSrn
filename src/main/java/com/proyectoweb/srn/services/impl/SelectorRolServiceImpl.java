/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services.impl;

import com.proyectoweb.srn.facade.GenericFacade;
import com.proyectoweb.srn.facade.SrnTblRolFacade;
import com.proyectoweb.srn.modelo.SrnTblRol;
import com.proyectoweb.srn.services.SelectorRolService;
import java.util.List;
import javax.inject.Inject;
import org.springframework.stereotype.Service;

@Service(SelectorRolServiceImpl.NAME_SERVICE)
public class SelectorRolServiceImpl extends GenericServiceImpl<SrnTblRol, Integer> implements SelectorRolService {

    static final String NAME_SERVICE = "selectorRolServiceImpl";

    @Inject
    private SrnTblRolFacade rolFacade;

    @Override
    public GenericFacade<SrnTblRol, Integer> getFacade() {
        return rolFacade;
    }

    @Override
    public List<SrnTblRol> findAll() {
        return rolFacade.findAll();
    }

}
