package com.usa.ciclo3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Ale Giraldo
 */
@Data
@Entity
@Table(name="CATEGORY")
public class Category implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    private String name;
    
    private String description;
    
    //Relación Category-Rooms
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy="name")
    @JsonIgnoreProperties("category")
    private List<Room> rooms;

}
