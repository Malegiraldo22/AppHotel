/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.ciclo3.service;

import com.usa.ciclo3.model.Admin;
import com.usa.ciclo3.repository.AdminRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ale Giraldo
 */
@Service
public class AdminService {
    
    @Autowired
    private AdminRepository adminRepository;
    
    public List<Admin> getAll(){
        return adminRepository.getAll();
    }
    
    public Optional<Admin> getAdmin(Integer id){
        return adminRepository.getAdmin(id);
    }
    
    public Admin save(Admin admin){
        if(admin.getIdAdmin() == null){
            return adminRepository.save(admin);
        } else {
            Optional<Admin> adminAux = adminRepository.getAdmin(admin.getIdAdmin());
            if(adminAux.isEmpty()){
                return adminRepository.save(admin);
            } else {
                return admin;
            }
        }
    }
    
    public Admin update(Admin admin){
        if(admin.getIdAdmin() != null){
            Optional<Admin> auxAdmin = adminRepository.getAdmin(admin.getIdAdmin());
            if(!auxAdmin.isEmpty()){
                if(admin.getName() != null){
                    auxAdmin.get().setName(admin.getName());
                }
                
                if(admin.getEmail() != null){
                    auxAdmin.get().setEmail(admin.getEmail());
                }
                
                if(admin.getPassword() != null){
                    auxAdmin.get().setPassword(admin.getPassword());
                }
                
                adminRepository.save(auxAdmin.get());
                return auxAdmin.get();
            } else {
                return admin;
            }
        } else {
            return admin;
        }
    }
    
    public Boolean delAdmin(Integer id){
        Boolean aBoolean = getAdmin(id).map(admin -> {adminRepository.delAdmin(admin);
        return true;
        }).orElse(false);
        return aBoolean;
    }
    
}
