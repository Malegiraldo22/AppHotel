/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.ciclo3.service;

import com.usa.ciclo3.model.Reservation;
import com.usa.ciclo3.model.custom.CountClient;
import com.usa.ciclo3.model.custom.StatusAmount;
import com.usa.ciclo3.repository.ReservationRepository;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ale Giraldo
 */
@Service
public class ReservationService {
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    public List<Reservation> getAll(){
        return reservationRepository.getAll();
    }
    
    public Optional<Reservation> getReservation(Integer id){
        return reservationRepository.getReservation(id);
    }
    
    public Reservation save(Reservation reservation){
        if(reservation.getIdReservation() == null){
            return reservationRepository.save(reservation);
        } else {
            Optional<Reservation> reservationAux = reservationRepository.getReservation(reservation.getIdReservation());
            if(reservationAux.isEmpty()){
                return reservationRepository.save(reservation);
            } else {
                return reservation;
            }
        }
    }
    
    public Reservation update(Reservation reservation){
        if(reservation.getIdReservation() == null){
            Optional<Reservation> reservationAux = reservationRepository.getReservation(reservation.getIdReservation());
            if(!reservationAux.isEmpty()){
                if(reservation.getStartDate() != null){
                    reservationAux.get().setStartDate(reservation.getStartDate());
                }
                
                if(reservation.getDevolutionDate() != null){
                    reservationAux.get().setDevolutionDate(reservation.getDevolutionDate());
                }
                reservationRepository.save(reservationAux.get());
                return reservationAux.get();
            } else {
                return reservation;
            }
        } else {
            return reservation;
        }
    }
    
    public Boolean delReservation(Integer id){
        Boolean aBoolean = getReservation(id).map(reservation -> {reservationRepository.delReservation(reservation);
        return true;
        }).orElse(false);
        return aBoolean;
    }
    
    public List<CountClient> getTopClient(){
        return reservationRepository.getTopClient();
    }
    
    public StatusAmount getStatusReport(){
        List<Reservation> completed = reservationRepository.getReservationByStatus("completed");
        List<Reservation> cancelled = reservationRepository.getReservationByStatus("cancelled");
        
        StatusAmount statusAmount = new StatusAmount(completed.size(), cancelled.size());
        return statusAmount;
    }
    
    public List<Reservation> getReservationPeriod(String d1, String d2){
        
        //Formato fecha: yyyy-MM-dd
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
        Date dateOne = new Date();
        Date dateTwo = new Date();
        
        try{
            dateOne = parser.parse(d1);
            dateTwo = parser.parse(d2);
        } catch(ParseException e){
            e.printStackTrace();
        }
        
        if(dateOne.before(dateTwo)){
            return reservationRepository.getReservationByDate(dateOne, dateTwo);
        } else {
            return new ArrayList<>();
        }
        
    }
}
