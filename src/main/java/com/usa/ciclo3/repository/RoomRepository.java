package com.usa.ciclo3.repository;

import com.usa.ciclo3.model.Room;
import com.usa.ciclo3.repository.crud.RoomCrudRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Ale Giraldo
 */
@Repository
public class RoomRepository {
    
    @Autowired
    private RoomCrudRepository roomCrudRepository;
    
    public List<Room> getAll(){
        return (List<Room>) roomCrudRepository.findAll();
    }
    
    public Optional<Room> getRoom(Integer id){
        return roomCrudRepository.findById(id);
    }
    
    public Room save(Room room){
        return roomCrudRepository.save(room);
    }
    
    public void delRoom(Room room){
        roomCrudRepository.delete(room);
    }
}
