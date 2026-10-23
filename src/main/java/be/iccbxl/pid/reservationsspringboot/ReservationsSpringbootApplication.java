package be.iccbxl.pid.reservationsspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Point d'entrée de l'application.
 * Sert à lancer le framework SpringBoot ainsi que le serveur web intégré.
 *
 * @author Julio Ramos do Carmo
 */
@SpringBootApplication
@EnableScheduling
public class ReservationsSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationsSpringbootApplication.class, args);
    }

}