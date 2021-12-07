/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.ciclo3.repository;

import com.usa.ciclo3.model.Score;
import com.usa.ciclo3.repository.crud.ScoreCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ale Giraldo
 */
@Repository
public class ScoreRepository {
    
    @Autowired
    private ScoreCrudRepository scoreCrudRepository;
    
    public List<Score> getAll(){
        return (List<Score>) scoreCrudRepository.findAll();
    }
    
    public Optional<Score> getScore(Integer id){
        return scoreCrudRepository.findById(id);
    }
    
    public Score save(Score score){
        return scoreCrudRepository.save(score);
    }
    
    public void delScore(Score score){
        scoreCrudRepository.delete(score);
    }
}
