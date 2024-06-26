package edu.tcu.cs.hogwartsartifactsonline;

import edu.tcu.cs.hogwartsartifactsonline.artifact.utils.IdWorker;
import jakarta.persistence.Id;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class HogwartsArtifactsOnlineApplication {

    public static void main(String[] args) {

        SpringApplication.run(HogwartsArtifactsOnlineApplication.class, args);

    }
    @Bean // idWorker object that can be managed by spring
    public IdWorker idWorker(){

        return new IdWorker(1,1);
    }





}
