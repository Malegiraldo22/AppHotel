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
@Table(name="CLIENT")
public class Client implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idClient;
    
    private String email;
    
    private String password;
    
    private String name;
    
    private Integer age;
    
    //Relación Client-Messages
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy="client")
    @JsonIgnoreProperties("client")
    private List<Message> messages;
    
    //Relación Client-Reservations
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy="client") 
    @JsonIgnoreProperties("client")
    private List<Reservation> reservations;
    
    
    

    
}
