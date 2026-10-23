package be.iccbxl.pid.reservationsspringboot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsspringboot.model.User;

/**
 * Accès aux données de la table users.
 *
 * @author Julio Ramos do Carmo
 */
public interface UserRepository extends CrudRepository<User, Long> {

    // Cherche un utilisateur par son login (unique)
    User findByLogin(String login);

    // Cherche un utilisateur par son email (peut ne rien trouver)
    Optional<User> findByEmail(String email);

    // Cherche les utilisateurs par leur nom de famille
    List<User> findByLastname(String lastname);

    // Cherche un utilisateur par son identifiant
    User findById(long id);
}