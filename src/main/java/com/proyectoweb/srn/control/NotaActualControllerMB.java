/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.control;

import com.proyectoweb.srn.componentes.autocomplete.AutocompletarMateria;
import com.proyectoweb.srn.componentes.autocomplete.AutocompletarUsuario;
import com.proyectoweb.srn.modelo.SrnTblMateriaUsuario;
import com.proyectoweb.srn.modelo.SrnTblNota;
import com.proyectoweb.srn.services.MateriaService;
import com.proyectoweb.srn.services.MateriaUsuarioService;
import com.proyectoweb.srn.services.NotaService;
import com.proyectoweb.srn.services.UsuarioService;
import com.proyectoweb.srn.to.UsuarioTO;
import com.proyectoweb.srn.utilidades.FacesUtils;
import com.proyectoweb.srn.utilidades.UtilidadesSeguridad;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.ViewExpiredException;
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

    @Inject
    private NotaService notaService;
    @Inject
    private UsuarioService usuarioService;
    @Inject
    private MateriaService materiaService;
    @Inject
    private MateriaUsuarioService materiaUsuarioService;

    private UsuarioTO usuarioTo;
    private SrnTblMateriaUsuario materiaUsuario;
    private String nombre;

    private boolean edit = false;
    private boolean form = false;

    double num1, num2, num3, notaAdi, notaProy, suma, promedio;

    private List<SrnTblNota> listNota;

    private AutocompletarUsuario autocompletarUsuario;
    private AutocompletarMateria autocompletarMateria;

    /**
     *
     */
    @PostConstruct
    @Override
    public void init() {
        try {
            listNota = new ArrayList<SrnTblNota>();
            usuarioSession();
            buscarTodos();
            autocompletar();

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
        String rol = usuarioTo.getRolCodigo().getStrDescripcion();
        switch (rol) {
            case "ADMINISTRADOR":
                listNota = notaService.buscarTodos();
                break;
            case "DOCENTE":
                listNota = notaService.buscarNotasAsignadasUsuario(usuarioTo.getIdUser(), 0);
                break;
            case "ESTUDIANTE":
                listNota = notaService.buscarNotasAsignadasUsuario(0, usuarioTo.getIdUser());
                break;
        }
    }

    public void autocompletar() {

        autocompletarUsuario = new AutocompletarUsuario() {

            private static final long serialVersionUID = 1L;

            @Override
            public UsuarioService usuarioService() {
                return usuarioService;
            }
        };

        autocompletarMateria = new AutocompletarMateria() {

            private static final long serialVersionUID = 1L;

            @Override
            public MateriaService materiaService() {
                return materiaService;
            }
        };

    }

    @Override
    public void verForm() {
        nota = new SrnTblNota();
        materiaUsuario = new SrnTblMateriaUsuario();
        id = 0;
        desc = "";
        num1 = 0.0;
        num2 = 0.0;
        num3 = 0.0;
        notaAdi = 0.0;
        notaProy = 0.0;
        promedio = 0.0;
        autocompletarMateria.setSeleccionado(null);
        autocompletarUsuario.setSeleccionado(null);
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
        num1 = FacesUtils.Redondear(r.getNumParcialI());
        num2 = FacesUtils.Redondear(r.getNumParcialIi());
        num3 = FacesUtils.Redondear(r.getNumParcialIii());
        notaAdi = r.getNumNotaAdicional();
        notaProy = FacesUtils.Redondear(r.getNumPryecto());
        promedio = r.getNumNotaFinal();
        autocompletarUsuario.setSeleccionado(nota.getNumCodMateriauser().getNumCodUsuario());
        autocompletarMateria.setSeleccionado(nota.getNumCodMateriauser().getNumCodMateria());
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
                    if (notaService.findById(id) == null) {
                        nota.setNumIdNota(id);

                        nota.setNumParcialI(num1);
                        nota.setNumParcialIi(num2);
                        nota.setNumParcialIii(num3);
                        nota.setNumNotaAdicional(notaAdi);
                        nota.setNumPryecto(notaProy);

                        suma = (FacesUtils.Redondear(num1 * 0.20) + FacesUtils.Redondear(num2 * 0.20) + FacesUtils.Redondear(num3 * 0.25) + notaAdi * 0.15 + FacesUtils.Redondear(notaProy * 0.20));
                        promedio = suma;

                        System.out.println("El estudiante aprovo con un promedio de: "
                                + FacesUtils.Redondear(num1 * 0.20) + " "
                                + FacesUtils.Redondear(num1 * 0.20) + " "
                                + FacesUtils.Redondear(num1 * 0.20) + " "
                                + notaAdi * 0.15 + " "
                                + FacesUtils.Redondear(notaProy * 0.20) + " R: "
                                + FacesUtils.Redondear(promedio));

                        materiaUsuario = materiaUsuarioService.findMateriaUser(autocompletarMateria.getSeleccionado().getNumIdMateria(), usuarioTo.getIdUser(), autocompletarUsuario.getSeleccionado().getIdUsuario());
                        nota.setNumNotaFinal(FacesUtils.Redondear(promedio));

                        if (notaService.findMateriaUser(materiaUsuario.getNumIdMateriauser()) != null) {
                            FacesUtils.addErrorMessage("El estudiante ya tiene notas creadas");
                        } else {
                            nota.setNumCodMateriauser(materiaUsuario);
                            notaService.create(nota);
                            FacesUtils.addInfoMessage("Registro exitoso");
                        }
//                    }
                    }
                } else {
                    nota.setNumParcialI(num1);
                    nota.setNumParcialIi(num2);
                    nota.setNumParcialIii(num3);
                    nota.setNumNotaAdicional(notaAdi);
                    nota.setNumPryecto(notaProy);

                    suma = (FacesUtils.Redondear(num1 * 0.20) + FacesUtils.Redondear(num2 * 0.20) + FacesUtils.Redondear(num3 * 0.25) + notaAdi * 0.15 + FacesUtils.Redondear(notaProy * 0.20));
                    promedio = suma;

                    System.out.println("El estudiante aprovo con un promedio de: "
                            + FacesUtils.Redondear(num1 * 0.20) + " "
                            + FacesUtils.Redondear(num1 * 0.20) + " "
                            + FacesUtils.Redondear(num1 * 0.20) + " "
                            + notaAdi * 0.15 + " "
                            + FacesUtils.Redondear(notaProy * 0.20) + " R: "
                            + FacesUtils.Redondear(promedio));

                    nota.setNumNotaFinal(FacesUtils.Redondear(promedio));
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
        try {
            if (autocompletarMateria.getSeleccionado() == null) {
                mensajeError("Por favor seleccione una materia");
                accion = false;
            }
            if (autocompletarUsuario.getSeleccionado() == null) {
                mensajeError("Por favor seleccione un estudiante");
                accion = false;
            }
            if (autocompletarMateria.getSeleccionado() != null && autocompletarUsuario.getSeleccionado() != null) {
                if (materiaUsuarioService.findMateriaUser(autocompletarMateria.getSeleccionado().getNumIdMateria(), usuarioTo.getIdUser(), autocompletarUsuario.getSeleccionado().getIdUsuario()) == null) {
                    mensajeError("Por favor valide si el usuario tiene asignado materias");
                    accion = false;
                }
            }
        } catch (Exception e) {
            FacesUtils.controlLog("SEVERE", "Error [preAction] en la clase NotaActualControllerMB: " + e.getMessage());
        }
        return accion;
    }

    public void usuarioSession() {
        try {
            if (FacesUtils.getSession().getAttribute("usuario") == null) {
                System.out.println("No hay registro de usuario");
                UtilidadesSeguridad.getControlSession("endsession.jsp");
            } else {
                usuarioTo = (UsuarioTO) FacesUtils.getSession().getAttribute("usuario");
                nombre = usuarioTo.getNombre() + " " + usuarioTo.getApellidos();
            }
        } catch (IllegalStateException ie) {
            FacesUtils.controlLog("SEVERE", "Error [IllegalStateException] en la clase NotaActualControllerMB: " + ie.getMessage());
            UtilidadesSeguridad.getControlSession("endsession.jsp");
        } catch (ViewExpiredException e) {
            FacesUtils.controlLog("SEVERE", "Error [ViewExpiredException] en la clase NotaActualControllerMB: " + e.getMessage());
            UtilidadesSeguridad.getControlSession("endsession.jsp");
        } catch (Exception ex) {
            FacesUtils.controlLog("SEVERE", "Error [Exception] en la clase ReglasDeNavegacion: " + ex.getMessage());
        }
    }

    public void mensajeError(String cadena) {
        RequestContext.getCurrentInstance().update("growl");
        FacesUtils.addErrorMessage(cadena);
    }

    public void mensajeInfo(String cadena) {
        RequestContext.getCurrentInstance().update("growl");
        FacesUtils.addInfoMessage(cadena);
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

    public AutocompletarUsuario getAutocompletarUsuario() {
        return autocompletarUsuario;
    }

    public void setAutocompletarUsuario(AutocompletarUsuario autocompletarUsuario) {
        this.autocompletarUsuario = autocompletarUsuario;
    }

    public AutocompletarMateria getAutocompletarMateria() {
        return autocompletarMateria;
    }

    public void setAutocompletarMateria(AutocompletarMateria autocompletarMateria) {
        this.autocompletarMateria = autocompletarMateria;
    }

}
