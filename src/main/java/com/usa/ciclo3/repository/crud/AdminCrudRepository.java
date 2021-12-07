/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.ciclo3.repository.crud;

import com.usa.ciclo3.model.Admin;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Ale Giraldo
 */
public interface AdminCrudRepository extends CrudRepository<Admin, Integer>{
    
}
