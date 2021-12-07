package com.usa.ciclo3.service;

import com.usa.ciclo3.model.Category;
import com.usa.ciclo3.repository.CategoryRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ale Giraldo
 */
@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    
    public List<Category> getAll(){
        return categoryRepository.getAll();
    }
    
    public Optional<Category> getCategory(Integer id){
        return categoryRepository.getCategory(id);
    }
    
    public Category save(Category category){
        if(category.getId() == null){
            return categoryRepository.save(category);
        } else {
            Optional<Category> categoryAux = categoryRepository.getCategory(category.getId());
            if(categoryAux.isEmpty()){
                return categoryRepository.save(category);
            } else {
                return category;
            }
        }
    }
    
    public Category update(Category category){
        if(category.getId() != null){ //Verifica si el id no está vacío
            Optional<Category> categoryAux = categoryRepository.getCategory(category.getId()); //Crea un auxiliar en el que se guarda el id del elemento
            if(!categoryAux.isEmpty()){ //Verifica que el id no sea vacío
                if(category.getName() != null){ //Verifica que el nombre no sea vacío
                    categoryAux.get().setName(category.getName()); //busca el nombre y sobreescribe el nombre actual que se consigue con .get()
                }
                
                if(category.getDescription() != null){//Verifica que la descripción no sea vacía
                    categoryAux.get().setDescription(category.getDescription());//Busca la desc actual y la sobreescribe
                }
                
                categoryRepository.save(categoryAux.get()); //Guarda el valor actual de categoryAux
                return categoryAux.get(); //Retorna el valor de categoryAux
            } else {
                return category; //Si name o description son vacios retorna el objeto original
            }
        } else {
            return category; //Si el id es null retorna el objeto original
        }
    }
    
    public Boolean delCategory(Integer id){
        Boolean aBoolean = getCategory(id).map(category -> {categoryRepository.delCategory(category);
        return true;
        }).orElse(false);
        return aBoolean;
    }
}

