/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.modelo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author TSI
 */
@Entity
@Table(name = "SRN_TBL_MENU_PERMISOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrnTblMenuPermisos.findAll", query = "SELECT s FROM SrnTblMenuPermisos s"),
    @NamedQuery(name = "SrnTblMenuPermisos.findByNumIdMenuPermiso", query = "SELECT s FROM SrnTblMenuPermisos s WHERE s.numIdMenuPermiso = :numIdMenuPermiso"),
    @NamedQuery(name = "SrnTblMenuPermisos.findByStrDescripcion", query = "SELECT s FROM SrnTblMenuPermisos s WHERE s.strDescripcion = :strDescripcion")})
public class SrnTblMenuPermisos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_ID_MENU_PERMISO")
    private Integer numIdMenuPermiso;
    @Size(max = 50)
    @Column(name = "STR_DESCRIPCION")
    private String strDescripcion;
    @JoinColumn(name = "NUM_COD_ROL", referencedColumnName = "NUM_ID_ROL")
    @ManyToOne(optional = false)
    private SrnTblRol numCodRol;

    public SrnTblMenuPermisos() {
    }

    public SrnTblMenuPermisos(Integer numIdMenuPermiso) {
        this.numIdMenuPermiso = numIdMenuPermiso;
    }

    public Integer getNumIdMenuPermiso() {
        return numIdMenuPermiso;
    }

    public void setNumIdMenuPermiso(Integer numIdMenuPermiso) {
        this.numIdMenuPermiso = numIdMenuPermiso;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    public SrnTblRol getNumCodRol() {
        return numCodRol;
    }

    public void setNumCodRol(SrnTblRol numCodRol) {
        this.numCodRol = numCodRol;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numIdMenuPermiso != null ? numIdMenuPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrnTblMenuPermisos)) {
            return false;
        }
        SrnTblMenuPermisos other = (SrnTblMenuPermisos) object;
        if ((this.numIdMenuPermiso == null && other.numIdMenuPermiso != null) || (this.numIdMenuPermiso != null && !this.numIdMenuPermiso.equals(other.numIdMenuPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "" + numIdMenuPermiso ;
    }
    
}
