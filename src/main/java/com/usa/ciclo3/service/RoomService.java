package com.usa.ciclo3.service;

import com.usa.ciclo3.model.Room;
import com.usa.ciclo3.repository.RoomRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ale Giraldo
 */
@Service
public class RoomService {
    
    @Autowired
    private RoomRepository roomRepository;
    
    public List<Room> getAll(){
        return roomRepository.getAll();
    }
    
    public Optional<Room> getRoom(Integer id){
        return roomRepository.getRoom(id);
    }
    
    public Room save(Room room){
        if(room.getId() == null){
            return roomRepository.save(room);
        } else {
            Optional<Room> roomAux = roomRepository.getRoom(room.getId());
            if(roomAux.isEmpty()){
                return roomRepository.save(room);
            } else {
                return room; //Lo retorna pero no lo guarda
            }
        }
    }
    
    public Room update(Room room){
        if(room.getId() != null){
            Optional<Room> roomAux = roomRepository.getRoom(room.getId());
            
            if(!roomAux.isEmpty()){
                
                if(room.getName() != null){
                    roomAux.get().setName(room.getName());
                }
                
                if(room.getHotel() != null){
                    roomAux.get().setHotel(room.getHotel());
                }
                
                if(room.getStars() != null){
                    roomAux.get().setStars(room.getStars());
                }
                
                if(room.getDescription() != null){
                    roomAux.get().setDescription(room.getDescription());
                }
                
                roomRepository.save(roomAux.get());
                return roomAux.get();
            } else {
                return room;
            }
        } else {
            return room;
        }
    }
    
    public Boolean delRoom(Integer id){
        Boolean aBoolean = getRoom(id).map(room -> {roomRepository.delRoom(room);
        return true;
        }).orElse(false);
        return aBoolean;
    }
}
