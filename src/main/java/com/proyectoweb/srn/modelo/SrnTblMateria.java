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
@Table(name = "SRN_TBL_MATERIA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SrnTblMateria.findAll", query = "SELECT s FROM SrnTblMateria s"),
    @NamedQuery(name = "SrnTblMateria.findByNumIdMateria", query = "SELECT s FROM SrnTblMateria s WHERE s.numIdMateria = :numIdMateria"),
    @NamedQuery(name = "SrnTblMateria.findByStrNombreMateria", query = "SELECT s FROM SrnTblMateria s WHERE s.strNombreMateria = :strNombreMateria"),
    @NamedQuery(name = "SrnTblMateria.findByNumIntensidadHoraria", query = "SELECT s FROM SrnTblMateria s WHERE s.numIntensidadHoraria = :numIntensidadHoraria")})
public class SrnTblMateria implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "NUM_ID_MATERIA")
    private Long numIdMateria;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "STR_NOMBRE_MATERIA")
    private String strNombreMateria;
    @Column(name = "NUM_INTENSIDAD_HORARIA")
    private Long numIntensidadHoraria;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numCodMateria")
    private List<SrnTblMateriaUsuario> srnTblMateriaUsuarioList;

    public SrnTblMateria() {
    }

    public SrnTblMateria(Long numIdMateria) {
        this.numIdMateria = numIdMateria;
    }

    public SrnTblMateria(Long numIdMateria, String strNombreMateria) {
        this.numIdMateria = numIdMateria;
        this.strNombreMateria = strNombreMateria;
    }

    public Long getNumIdMateria() {
        return numIdMateria;
    }

    public void setNumIdMateria(Long numIdMateria) {
        this.numIdMateria = numIdMateria;
    }

    public String getStrNombreMateria() {
        return strNombreMateria;
    }

    public void setStrNombreMateria(String strNombreMateria) {
        this.strNombreMateria = strNombreMateria;
    }

    public Long getNumIntensidadHoraria() {
        return numIntensidadHoraria;
    }

    public void setNumIntensidadHoraria(Long numIntensidadHoraria) {
        this.numIntensidadHoraria = numIntensidadHoraria;
    }

    @XmlTransient
    public List<SrnTblMateriaUsuario> getSrnTblMateriaUsuarioList() {
        return srnTblMateriaUsuarioList;
    }

    public void setSrnTblMateriaUsuarioList(List<SrnTblMateriaUsuario> srnTblMateriaUsuarioList) {
        this.srnTblMateriaUsuarioList = srnTblMateriaUsuarioList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numIdMateria != null ? numIdMateria.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SrnTblMateria)) {
            return false;
        }
        SrnTblMateria other = (SrnTblMateria) object;
        if ((this.numIdMateria == null && other.numIdMateria != null) || (this.numIdMateria != null && !this.numIdMateria.equals(other.numIdMateria))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.proyectoweb.srn.persistencia.SrnTblMateria[ numIdMateria=" + numIdMateria + " ]";
    }
    
}
