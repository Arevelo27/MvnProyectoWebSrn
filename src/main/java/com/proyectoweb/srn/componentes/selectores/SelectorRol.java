/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.componentes.selectores;

import com.proyectoweb.srn.modelo.SrnTblRol;
import com.proyectoweb.srn.services.SelectorRolService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author TSI
 */
@ManagedBean
@RequestScoped
public class SelectorRol extends SelectorBase<SrnTblRol, Integer> {

    @Inject
    private SelectorRolService rolService;

    @PostConstruct
    public void init() {
        cargarLista();
    }

    @Override
    public void cargarLista() {
        List<SrnTblRol> listado = rolService.findAll();
        setLista(new ArrayList<SelectItem>());
        for (SrnTblRol rol : listado) {
            getLista().add(new SelectItem(rol.getNumIdRol(), rol.getStrDescripcion()));
        }
    }

}
