package be.iccbxl.pid.reservationsspringboot.repository;

import org.springframework.data.repository.CrudRepository;

import be.iccbxl.pid.reservationsspringboot.model.Type;

/**
 * Accès aux données de la table types.
 *
 * @author Julio Ramos do Carmo
 */
public interface TypeRepository extends CrudRepository<Type, Long> {

    // Cherche un type par son nom (ex. « comédien »)
    Type findByType(String type);
}