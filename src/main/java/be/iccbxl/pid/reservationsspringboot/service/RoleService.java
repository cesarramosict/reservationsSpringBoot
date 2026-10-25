package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.Role;
import be.iccbxl.pid.reservationsspringboot.repository.RoleRepository;

/**
 * Logique métier des rôles.
 *
 * @author Julio Ramos do Carmo
 */
@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    // Retourne la liste de tous les rôles
    public List<Role> getAll() {
        List<Role> roles = new ArrayList<>();
        repository.findAll().forEach(roles::add);
        return roles;
    }

    // Retourne un rôle à partir de son identifiant (ou null s'il n'existe pas)
    public Role get(long id) {
        return repository.findById(id).orElse(null);
    }

    // Ajoute un nouveau rôle
    public void add(Role role) {
        repository.save(role);
    }

    // Modifie un rôle existant
    public void update(long id, Role role) {
        repository.save(role);
    }

    // Supprime un rôle à partir de son identifiant
    public void delete(long id) {
        repository.deleteById(id);
    }
}