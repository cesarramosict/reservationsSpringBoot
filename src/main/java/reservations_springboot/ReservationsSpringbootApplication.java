package reservations_springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * Poitn d'entrée de l'application.
 * Sert à lancer le framework Sring Boot ainsi que le serveur web intégré.
 *
 * @author Julio Ramos do Carmo
 */


@SpringBootApplication
public class ReservationsSpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReservationsSpringbootApplication.class, args);
	}

}
