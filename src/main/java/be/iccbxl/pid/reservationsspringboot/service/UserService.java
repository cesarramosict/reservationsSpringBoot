package be.iccbxl.pid.reservationsspringboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import be.iccbxl.pid.reservationsspringboot.dto.UserRegistrationDto;
import be.iccbxl.pid.reservationsspringboot.model.User;
import be.iccbxl.pid.reservationsspringboot.model.UserRole;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    // Vrai si le login et l'email ne sont pas encore utilisés
    public boolean isLoginAndEmailAvailable(String login, String email) {
        return !userRepository.existsByLogin(login) && !userRepository.existsByEmail(email);
    }

    // Crée un nouveau membre à partir du formulaire d'inscription
    public void registerFromDto(UserRegistrationDto dto) {
        User user = new User();
        user.setFirstname(dto.getFirstname());
        user.setLastname(dto.getLastname());
        user.setLogin(dto.getLogin());
        user.setEmail(dto.getEmail());
        user.setLangue(dto.getLangue());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(UserRole.MEMBER);

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