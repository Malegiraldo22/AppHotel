/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.ciclo3.repository;


import com.usa.ciclo3.model.Client;
import com.usa.ciclo3.model.Reservation;
import com.usa.ciclo3.model.custom.CountClient;
import com.usa.ciclo3.repository.crud.ReservationCrudRepository;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ale Giraldo
 */
@Repository
public class ReservationRepository {
    
    @Autowired
    private ReservationCrudRepository reservationCrudRepository;
    
    public List<Reservation> getAll(){
        return (List<Reservation>) reservationCrudRepository.findAll();
    }
    
    public Optional<Reservation> getReservation(Integer id){
        return reservationCrudRepository.findById(id);
    }
    
    public Reservation save(Reservation reservation){
        return reservationCrudRepository.save(reservation);
    }
    
    public void delReservation(Reservation reservation){
        reservationCrudRepository.delete(reservation);
    }
    
    public List<Reservation> getReservationByStatus(String status){
        return reservationCrudRepository.findAllByStatus(status);
    }
    
    public List<Reservation> getReservationByDate(Date dateOne, Date dateTwo){
        return reservationCrudRepository.findAllByStartDateAfterAndStartDateBefore(dateOne, dateTwo);
    }
    
    public List<CountClient> getTopClient(){
        List<CountClient> result = new ArrayList<>();
        List<Object[]> report = reservationCrudRepository.countTotalClientByReservation();
        
        for(int i=0; i < report.size(); i++){
            
            Client client = (Client) report.get(i)[0];
            Long count = (Long) report.get(i)[1];
            
            CountClient cc = new CountClient(count, client);
            
            result.add(cc);
        }
        
        return result;
    }
}
