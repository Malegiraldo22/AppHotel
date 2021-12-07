package com.usa.ciclo3.model;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

/**
 *
 * @author Ale Giraldo
 */
@Data
@Entity
@Table(name="RESERVATION")
public class Reservation implements Serializable{
    
    @Id //Pone como llave principal al ID
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Genera un autoincremental
    private Integer idReservation;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date devolutionDate;
    
    @Column(name="status")
    private String status = "created";
    
    //Relación Room-Reservations
    @ManyToOne
    @JoinColumn(name="description")
    @JsonIgnoreProperties("reservations")
    private Room room;
    
    //Relación Client-Reservations
    @ManyToOne
    @JoinColumn(name="name")
    @JsonIgnoreProperties({"reservations", "messages"})
    private Client client;
    
    //Relación Reservation-Score
    @OneToOne(cascade = {CascadeType.REMOVE},mappedBy="reservation")
    @JsonIgnoreProperties("reservation")
    private Score score;

}
