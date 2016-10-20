/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.modelo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TSI
 */
@Entity
@Table(name = "SRN_TBL_ESTADO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrnTblEstado.findAll", query = "SELECT s FROM SrnTblEstado s"),
    @NamedQuery(name = "SrnTblEstado.findByStrCodEstado", query = "SELECT s FROM SrnTblEstado s WHERE s.strCodEstado = :strCodEstado"),
    @NamedQuery(name = "SrnTblEstado.findByStrDescripcion", query = "SELECT s FROM SrnTblEstado s WHERE s.strDescripcion = :strDescripcion")})
public class SrnTblEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 1)
    @Column(name = "STR_COD_ESTADO")
    private String strCodEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STR_DESCRIPCION")
    private String strDescripcion;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "strEstado")
    private List<SrnTblUsuario> srnTblUsuarioList;

    public SrnTblEstado() {
    }

    public SrnTblEstado(String strCodEstado) {
        this.strCodEstado = strCodEstado;
    }

    public SrnTblEstado(String strCodEstado, String strDescripcion) {
        this.strCodEstado = strCodEstado;
        this.strDescripcion = strDescripcion;
    }

    public String getStrCodEstado() {
        return strCodEstado;
    }

    public void setStrCodEstado(String strCodEstado) {
        this.strCodEstado = strCodEstado;
    }

    public String getStrDescripcion() {
        return strDescripcion;
    }

    public void setStrDescripcion(String strDescripcion) {
        this.strDescripcion = strDescripcion;
    }

    @XmlTransient
    public List<SrnTblUsuario> getSrnTblUsuarioList() {
        return srnTblUsuarioList;
    }

    public void setSrnTblUsuarioList(List<SrnTblUsuario> srnTblUsuarioList) {
        this.srnTblUsuarioList = srnTblUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (strCodEstado != null ? strCodEstado.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrnTblEstado)) {
            return false;
        }
        SrnTblEstado other = (SrnTblEstado) object;
        if ((this.strCodEstado == null && other.strCodEstado != null) || (this.strCodEstado != null && !this.strCodEstado.equals(other.strCodEstado))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoweb.srn.persistencia.SrnTblEstado[ strCodEstado=" + strCodEstado + " ]";
    }
    
}
