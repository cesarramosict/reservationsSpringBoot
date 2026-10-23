package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.model.User;
import be.iccbxl.pid.reservationsspringboot.repository.UserRepository;

/**
 * Logique métier des utilisateurs : fait le lien entre les contrôleurs et le repository.
 *
 * @author Julio Ramos do Carmo
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Retourne la liste de tous les utilisateurs
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    // Retourne un utilisateur à partir de son identifiant
    public User getUser(long id) {
        return userRepository.findById(id);
    }
    // Retourne l'utilisateur qui possède cet email, ou null s'il n'existe pas
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    // Ajoute un nouvel utilisateur
    public void addUser(User user) {
        userRepository.save(user);
    }

    // Modifie un utilisateur existant
    public void updateUser(long id, User user) {
        userRepository.save(user);
    }

    // Supprime un utilisateur à partir de son identifiant
    public void deleteUser(long id) {
        userRepository.deleteById(id);
    }
}