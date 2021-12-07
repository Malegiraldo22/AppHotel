/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.ciclo3.repository.crud;


import com.usa.ciclo3.model.Reservation;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author Ale Giraldo
 */
public interface ReservationCrudRepository extends CrudRepository<Reservation, Integer>{
    
    //Conteo de reservas por intervalo de fechas
    public List<Reservation> findAllByStartDateAfterAndStartDateBefore(Date dateOne, Date dateTwo); //Spring crea la consulta SQL
    
    //Conteo Reservas completadas vs canceladas
    public List<Reservation> findAllByStatus(String status);
    
    //Top Mejores clientes
    @Query("SELECT r.client, COUNT(r.client) FROM Reservation AS r GROUP BY r.client ORDER BY COUNT(r.client) DESC") //Consulta en SQL nativo
    public List<Object[]> countTotalClientByReservation();
    //Se guarda a una lista de objetos para tener libertad de manejar la información
}
