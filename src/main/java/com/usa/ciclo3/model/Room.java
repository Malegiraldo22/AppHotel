package com.usa.ciclo3.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Alejandro Giraldo
 * 
 * Se realiza la anotación Data para crear los Getters y Setters con Lombok
 * Entity declara la clase como una entidad
 * Table le da el nombre a la tabla en la base de datos
 * Se implementa Serializable para transformar el objeto en una serie de bits
 */
@Data
@Entity
@Table(name="ROOM")
public class Room implements Serializable{
    /**
     * Anotación que determina cual es la llave principal
     */
    @Id
    /**
     * Anotación que genera el autoincremental en la tabla creada
     */
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    /**
     * Declaración de la columna Id de tipo integer
     */
    private Integer id;
    /**
     * Declaración de la columna name de tipo String
     */
    private String name;
    /**
     * Declaración de la columna hotel de tipo String
     */
    private String hotel;
    /**
     * Declaración de la columna Stars de tipo Integer
     */
    private Integer stars;
    /**
     * Declaración de la columna description de tipo String
     */
    private String description;
    
    /**
     * Relación tabla Category y tabla Room
     * Se unifica la información en una nueva columna llamada Category
     * Se ignoran las propiedades de la propiedad rooms que contiene toda la
     * información de la tabla room con el fin de evitar una recursión infinita
     * Se guarda la información en una columna llamada Category
     */
    @ManyToOne
    @JoinColumn(name="category")
    @JsonIgnoreProperties("rooms") 
    private Category category;
    
    /**
     * Relación tabla Room y tabla Messages
     * Se declara la propiedad cascade con el fin de que la información sea 
     * modificada o eliminada una vez se vea afectada la tabla relacionada
     * La información se guarda de acuerdo a la tabla room
     * Se ignoran las propiedades de la propiedad rooms que contiene toda la
     * información de la tabla room y client con el fin de evitar una recursión infinita
     * Se guarda la información en una columna llamada Messages
     */
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy="room")
    @JsonIgnoreProperties({"room", "client"})
    private List<Message> messages;
    
    /**
     * Se declara la propiedad cascade con el fin de que la información sea 
     * modificada o eliminada una vez se vea afectada la tabla relacionada
     * Relación tabla Room y tabla Reservations
     * La información se guarda de acuerdo a la tabla room
     * Se ignoran las propiedades de la propiedad rooms que contiene toda la
     * información de la tabla room y client con el fin de evitar una recursión infinita
     * Se guarda la información en una columna llamada reservations
     */
    @OneToMany(cascade = {CascadeType.PERSIST}, mappedBy="room")
    @JsonIgnoreProperties({"room", "client"})
    private List<Reservation> reservations;

    
}
