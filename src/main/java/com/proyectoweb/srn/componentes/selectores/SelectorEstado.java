/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.componentes.selectores;

import com.proyectoweb.srn.modelo.SrnTblEstado;
import com.proyectoweb.srn.services.SelectorEstadoService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;

/**
 *
 * @author Andres-Desarrollo2
 */
@ManagedBean
@RequestScoped
public class SelectorEstado extends SelectorBase<SrnTblEstado, Integer> {

    @Inject
    private SelectorEstadoService rolService;

    @PostConstruct
    public void init() {
        cargarLista();
    }

    @Override
    public void cargarLista() {
        List<SrnTblEstado> listado = rolService.findAll();
        setLista(new ArrayList<SelectItem>());
        for (SrnTblEstado estado : listado) {
            getLista().add(new SelectItem(estado.getStrCodEstado(), estado.getStrDescripcion()));
        }
    }

}
