/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.control;

import com.proyectoweb.srn.modelo.SrnTblNota;
import com.proyectoweb.srn.services.NotaService;
import com.proyectoweb.srn.utilidades.FacesUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import org.primefaces.context.RequestContext;

/**
 *
 * @author TSI
 */
@ManagedBean(name = "notaActualBn")
@ViewScoped
public class NotaActualControllerMB implements GenericBean<SrnTblNota>, Serializable {

    private SrnTblNota nota;
    private int id;
    private String desc;

    /*Debemos cambiar este ejb e investigar otro servicio*/
//    @ManagedProperty(value = "#{notaService}")
    @Inject
    private NotaService notaService;

    private boolean edit = false;
    private boolean form = false;

    private List<SrnTblNota> listNota;

    /**
     *
     */
    @PostConstruct
    @Override
    public void init() {
        try {
            listNota = new ArrayList<SrnTblNota>();
            buscarTodos();
        } catch (Exception e) {
            FacesUtils.controlLog("SEVERE", "Error en la clase LoginControllerMB del metodo init: " + e.getMessage());
        }
    }

    /**
     *
     * @throws Exception
     */
    @Override
    public void buscarTodos() throws Exception {
        listNota = notaService.buscarTodos();
    }

    @Override
    public void verForm() {
        nota = new SrnTblNota();
        id = 0;
        desc = "";
        form = true;
        edit = false;
    }

    @Override
    public void volverForm() {
        try {
            form = false;
            edit = false;
            buscarTodos();
        } catch (Exception e) {
            FacesUtils.controlLog("SEVERE", "Error en la clase LoginControllerMB del metodo volverForm: " + e.getMessage());
        }
    }

    /**
     *
     * @param r
     */
    @Override
    public void renderizarItem(SrnTblNota r) {
        nota = r;
//        desc = r.getStrDescripcion();
        form = true;
        edit = true;
    }

    @Override
    public void eliminarItem(SrnTblNota r) {
        try {
            notaService.remove(r);
            buscarTodos();
        } catch (Exception e) {
            FacesUtils.controlLog("SEVERE", "Error en la clase LoginControllerMB del metodo eliminarItem: " + e.getMessage());
        }
    }

    @Override
    public String aceptar() {
        String navegacion = "";
        try {
            if (preAction()) {
                if (!edit) {
                    id = notaService.findMaxId();
                    if (notaService.find(id) == null) {
                        nota.setNumIdNota(id);
//                        nota.setStrDescripcion(desc);
                        notaService.create(nota);

                        vaciarVariables();

                        RequestContext.getCurrentInstance();
                        FacesUtils.addInfoMessage("Registro exitoso");
//                    }
                    }
                } else {
//                    nota.setStrDescripcion(desc);
                    notaService.edit(nota);

                    RequestContext.getCurrentInstance();
                    FacesUtils.addInfoMessage("Actualizacón exitosa");
                }
            }
        } catch (Exception e) {
            FacesUtils.controlLog("SEVERE", "Error en la clase RolControllerMB del metodo aceptar: " + e.getMessage());
        }
        return navegacion;
    }

    public void vaciarVariables() {
        desc = "";
        id = 0;
        nota = new SrnTblNota();
    }

    @Override
    public boolean preAction() {
        boolean accion = true;
        return accion;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public SrnTblNota getRol() {
        return nota;
    }

    public void setRol(SrnTblNota rol) {
        this.nota = rol;
    }

    public boolean isForm() {
        return form;
    }

    public void setForm(boolean form) {
        this.form = form;
    }

    public List<SrnTblNota> getListNota() {
        return listNota;
    }

    public void setListNota(List<SrnTblNota> listNota) {
        this.listNota = listNota;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
