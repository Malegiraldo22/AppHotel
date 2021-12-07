package com.usa.ciclo3.service;

import com.usa.ciclo3.model.Client;
import com.usa.ciclo3.repository.ClientRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Ale Giraldo
 */
@Service
public class ClientService {
    
    @Autowired
    private ClientRepository clientRepository;
    
    public List<Client> getAll(){
        return clientRepository.getAll();
    }
    
    public Optional<Client> getClient(Integer id){
        return clientRepository.getClient(id);
    }
    
    public Client save(Client client){
        if(client.getIdClient()== null){
            return clientRepository.save(client);
        } else {
            Optional<Client> clientAux = clientRepository.getClient(client.getIdClient());
            if(clientAux.isEmpty()){
                return clientRepository.save(client);
            } else {
                return client;
            }
        }
    }
    
    public Client update(Client client){
        if(client.getIdClient() != null){
            Optional<Client> auxClient = clientRepository.getClient(client.getIdClient());
            if(!auxClient.isEmpty()){
                if(client.getEmail() != null){
                    auxClient.get().setEmail(client.getEmail());
                }
                
                if(client.getPassword() != null){
                    auxClient.get().setPassword(client.getPassword());
                }
                
                if(client.getName() != null){
                    auxClient.get().setName(client.getName());
                }
                
                if(client.getAge() != null){
                    auxClient.get().setAge(client.getAge());
                }
                
                clientRepository.save(auxClient.get());
                return auxClient.get();
            } else {
                return client;
            }
        } else {
            return client;
        }
    }
    
    public Boolean delClient(Integer id){
        Boolean aBoolean = getClient(id).map(client -> {clientRepository.delClient(client);
        return true;
        }).orElse(false);
        return aBoolean;
    }
}
