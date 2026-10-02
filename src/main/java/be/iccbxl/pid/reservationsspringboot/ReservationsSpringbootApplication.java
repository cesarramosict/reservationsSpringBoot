package be.iccbxl.pid.reservationsspringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Point d'entrée de l'application.
 * Sert à lancer le framework StringBoot ainsi que le serveur web intégré.
 *
 * @author Julio Ramos do Carmo
 */


@SpringBootApplication
public class ReservationsSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationsSpringbootApplication.class, args);
	}

}
