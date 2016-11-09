/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.componentes.selectores;

import com.proyectoweb.srn.modelo.SrnTblGenero;
import com.proyectoweb.srn.selector.services.SelectorGeneroService;
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
public class SelectorGenero extends SelectorBase<SrnTblGenero, String> {

    @Inject
    private SelectorGeneroService generoService;

    @PostConstruct
    public void init() {
        cargarLista();
    }

    @Override
    public void cargarLista() {
        List<SrnTblGenero> listado = generoService.findAll();
        setLista(new ArrayList<SelectItem>());
        for (SrnTblGenero genero : listado) {
            getLista().add(new SelectItem(genero.getStrCodGenero(), genero.getStrDescripcion()));
        }
    }

}
