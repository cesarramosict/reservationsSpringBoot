package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Type;
import be.iccbxl.pid.reservationsspringboot.repository.TypeRepository;

/**
 * Logique métier des types d'artistes.
 *
 * @author Julio Ramos do Carmo
 */
@Service
public class TypeService {

    @Autowired
    private TypeRepository repository;

    // Retourne la liste de tous les types
    public List<Type> getAll() {
        List<Type> types = new ArrayList<>();
        repository.findAll().forEach(types::add);
        return types;
    }

    // Retourne un type à partir de son identifiant (ou null s'il n'existe pas)
    public Type get(long id) {
        return repository.findById(id).orElse(null);
    }

    // Ajoute un nouveau type
    public void add(Type type) {
        repository.save(type);
    }

    // Modifie un type existant
    public void update(long id, Type type) {
        repository.save(type);
    }

    // Supprime un type à partir de son identifiant
    public void delete(long id) {
        repository.deleteById(id);
    }
}