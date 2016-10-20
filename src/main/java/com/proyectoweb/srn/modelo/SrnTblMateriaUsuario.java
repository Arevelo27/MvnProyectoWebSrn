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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author TSI
 */
@Entity
@Table(name = "SRN_TBL_MATERIA_USUARIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrnTblMateriaUsuario.findAll", query = "SELECT s FROM SrnTblMateriaUsuario s"),
    @NamedQuery(name = "SrnTblMateriaUsuario.findByNumIdMateriauser", query = "SELECT s FROM SrnTblMateriaUsuario s WHERE s.numIdMateriauser = :numIdMateriauser")})
public class SrnTblMateriaUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_ID_MATERIAUSER")
    private Long numIdMateriauser;
    @JoinColumn(name = "NUM_COD_USUARIO", referencedColumnName = "NUM_ID_USUARIO")
    @ManyToOne(optional = false)
    private SrnTblUsuario numCodUsuario;
    @JoinColumn(name = "NUM_COD_MATERIA", referencedColumnName = "NUM_ID_MATERIA")
    @ManyToOne(optional = false)
    private SrnTblMateria numCodMateria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numCodMateriauser")
    private List<SrnTblNota> srnTblNotaList;

    public SrnTblMateriaUsuario() {
    }

    public SrnTblMateriaUsuario(Long numIdMateriauser) {
        this.numIdMateriauser = numIdMateriauser;
    }

    public Long getNumIdMateriauser() {
        return numIdMateriauser;
    }

    public void setNumIdMateriauser(Long numIdMateriauser) {
        this.numIdMateriauser = numIdMateriauser;
    }

    public SrnTblUsuario getNumCodUsuario() {
        return numCodUsuario;
    }

    public void setNumCodUsuario(SrnTblUsuario numCodUsuario) {
        this.numCodUsuario = numCodUsuario;
    }

    public SrnTblMateria getNumCodMateria() {
        return numCodMateria;
    }

    public void setNumCodMateria(SrnTblMateria numCodMateria) {
        this.numCodMateria = numCodMateria;
    }

    @XmlTransient
    public List<SrnTblNota> getSrnTblNotaList() {
        return srnTblNotaList;
    }

    public void setSrnTblNotaList(List<SrnTblNota> srnTblNotaList) {
        this.srnTblNotaList = srnTblNotaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numIdMateriauser != null ? numIdMateriauser.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrnTblMateriaUsuario)) {
            return false;
        }
        SrnTblMateriaUsuario other = (SrnTblMateriaUsuario) object;
        if ((this.numIdMateriauser == null && other.numIdMateriauser != null) || (this.numIdMateriauser != null && !this.numIdMateriauser.equals(other.numIdMateriauser))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoweb.srn.persistencia.SrnTblMateriaUsuario[ numIdMateriauser=" + numIdMateriauser + " ]";
    }
    
}
