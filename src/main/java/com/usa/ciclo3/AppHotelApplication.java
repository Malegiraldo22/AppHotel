package com.usa.ciclo3;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.web.bind.annotation.RestController;

@EntityScan(basePackages = {"com.usa.ciclo3.model"}) //Busca todas las entidades(Tablas)
@SpringBootApplication
public class AppHotelApplication{
   
    public static void main(String[] args) {
        SpringApplication.run(AppHotelApplication.class, args);
    }

}
