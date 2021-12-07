/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.usa.ciclo3.service;

import com.usa.ciclo3.model.Score;
import com.usa.ciclo3.repository.ScoreRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ale Giraldo
 */
@Service
public class ScoreService {
    
    @Autowired
    private ScoreRepository scoreRepository;
    
    public List<Score> getAll(){
        return scoreRepository.getAll();
    }
    
    public Optional<Score> getScore(Integer id){
        return scoreRepository.getScore(id);
    }
    
    public Score save(Score score){
        if(score.getIdScore() == null){
            return scoreRepository.save(score);
        } else {
            Optional<Score> scoreAux = scoreRepository.getScore(score.getIdScore());
            if(scoreAux.isEmpty()){
                return scoreRepository.save(score);
            } else {
                return score;
            }
        }
    }
    
    public Score update(Score score){
        if(score.getIdScore() != null){
            Optional<Score> scoreAux = scoreRepository.getScore(score.getIdScore());
            if(!scoreAux.isEmpty()){
                
                if(score.getMessageText() != null){
                    scoreAux.get().setMessageText(score.getMessageText());
                }
                
                if(score.getStars() != null){
                    scoreAux.get().setStars(score.getStars());
                }
                
                scoreRepository.save(scoreAux.get());
                return scoreAux.get();
            } else {
                return score;
            }
        } else {
            return score;
        }
    }
    
    public Boolean delScore(Integer id){
        Boolean aBoolean = getScore(id).map(score -> {scoreRepository.delScore(score);
        return true;
        }).orElse(false);
        return aBoolean;
    }
}
