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
@Table(name = "SRN_TBL_PERMISOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrnTblPermisos.findAll", query = "SELECT s FROM SrnTblPermisos s"),
    @NamedQuery(name = "SrnTblPermisos.findByNumIdPermiso", query = "SELECT s FROM SrnTblPermisos s WHERE s.numIdPermiso = :numIdPermiso"),
    @NamedQuery(name = "SrnTblPermisos.findByStrDescripcion", query = "SELECT s FROM SrnTblPermisos s WHERE s.strDescripcion = :strDescripcion")})
public class SrnTblPermisos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_ID_PERMISO")
    private Integer numIdPermiso;
    @Size(max = 50)
    @Column(name = "STR_DESCRIPCION")
    private String strDescripcion;
    @JoinColumn(name = "NUM_COD_ROL", referencedColumnName = "NUM_ID_ROL")
    @ManyToOne(optional = false)
    private SrnTblRol numCodRol;

    public SrnTblPermisos() {
    }

    public SrnTblPermisos(Integer numIdPermiso) {
        this.numIdPermiso = numIdPermiso;
    }

    public Integer getNumIdPermiso() {
        return numIdPermiso;
    }

    public void setNumIdPermiso(Integer numIdPermiso) {
        this.numIdPermiso = numIdPermiso;
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
        hash += (numIdPermiso != null ? numIdPermiso.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrnTblPermisos)) {
            return false;
        }
        SrnTblPermisos other = (SrnTblPermisos) object;
        if ((this.numIdPermiso == null && other.numIdPermiso != null) || (this.numIdPermiso != null && !this.numIdPermiso.equals(other.numIdPermiso))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoweb.srn.persistencia.SrnTblPermisos[ numIdPermiso=" + numIdPermiso + " ]";
    }
    
}
