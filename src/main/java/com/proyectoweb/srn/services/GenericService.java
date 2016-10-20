/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.proyectoweb.srn.services;

import com.proyectoweb.srn.facade.GenericFacade;
import java.io.Serializable;

/**
 *
 * @author TSI
 * @param <T>
 * @param <PK>
 */
public interface GenericService<T, PK extends Serializable> extends GenericFacade<T, PK> {

}
