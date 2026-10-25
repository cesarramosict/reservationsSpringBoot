package be.iccbxl.pid.reservationsspringboot.repository;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsspringboot.model.Role;

/**
 * Accès aux données de la table roles.
 *
 * @author Julio Ramos do Carmo
 */
public interface RoleRepository extends CrudRepository<Role, Long> {

    // Cherche un rôle par son nom (ex. « admin »)
    Role findByRole(String role);
}