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

    double num1, num2, num3, notaAdi, notaProy, suma, promedio;

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
        num1 = 0.0;
        num2 = 0.0;
        num3 = 0.0;
        notaAdi = 0.0;
        notaProy = 0.0;
        promedio = 0.0;
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
        num1 = Redondear(r.getNumParcialI());
        num2 = Redondear(r.getNumParcialIi());
        num3 = Redondear(r.getNumParcialIii());
        notaAdi = r.getNumNotaAdicional();
        notaProy = Redondear(r.getNumPryecto());
        promedio = r.getNumNotaFinal();
        form = true;
        edit = true;
        //multiplica por 50 y divide por 10
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

                        nota.setNumParcialI(num1);
                        nota.setNumParcialIi(num2);
                        nota.setNumParcialIii(num3);
                        nota.setNumNotaAdicional(notaAdi);
                        nota.setNumPryecto(notaProy);

                        suma = (Redondear(num1 * 0.20) + Redondear(num2 * 0.20) + Redondear(num3 * 0.25) + notaAdi * 0.15 + Redondear(notaProy * 0.20));
                        promedio = suma;

                        System.out.println("El estudiante aprovo con un promedio de: "
                                + Redondear(num1 * 0.20) + " "
                                + Redondear(num1 * 0.20) + " "
                                + Redondear(num1 * 0.20) + " "
                                + notaAdi * 0.15 + " "
                                + Redondear(notaProy * 0.20) + " R: "
                                + Redondear(promedio));
//                        notaService.create(nota);

                        //vaciarVariables();
                        RequestContext.getCurrentInstance();
                        FacesUtils.addInfoMessage("Registro exitoso");
//                    }
                    }
                } else {
                    nota.setNumParcialI(num1);
                    nota.setNumParcialIi(num2);
                    nota.setNumParcialIii(num3);
                    nota.setNumNotaAdicional(notaAdi);
                    nota.setNumPryecto(notaProy);

                    suma = (Redondear(num1 * 0.20) + Redondear(num2 * 0.20) + Redondear(num3 * 0.25) + notaAdi * 0.15 + Redondear(notaProy * 0.20));
                    promedio = suma;

                    System.out.println("El estudiante aprovo con un promedio de: "
                            + Redondear(num1 * 0.20) + " "
                            + Redondear(num1 * 0.20) + " "
                            + Redondear(num1 * 0.20) + " "
                            + notaAdi * 0.15 + " "
                            + Redondear(notaProy * 0.20) + " R: "
                            + Redondear(promedio));

                    nota.setNumNotaFinal(Redondear(promedio));
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

    public double Redondear(double numero) {
        return Math.rint(numero * 100) / 100;
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

    public SrnTblNota getNota() {
        return nota;
    }

    public void setNota(SrnTblNota nota) {
        this.nota = nota;
    }

    public double getNum1() {
        return num1;
    }

    public void setNum1(double num1) {
        this.num1 = num1;
    }

    public double getNum2() {
        return num2;
    }

    public void setNum2(double num2) {
        this.num2 = num2;
    }

    public double getNum3() {
        return num3;
    }

    public void setNum3(double num3) {
        this.num3 = num3;
    }

    public double getNotaAdi() {
        return notaAdi;
    }

    public void setNotaAdi(double notaAdi) {
        this.notaAdi = notaAdi;
    }

    public double getPromedio() {
        return promedio;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public double getNotaProy() {
        return notaProy;
    }

    public void setNotaProy(double notaProy) {
        this.notaProy = notaProy;
    }

}
