package com.example.cinema.service;

import com.example.cinema.exception.EntityNotFoundException;
import com.example.cinema.model.Role;
import com.example.cinema.model.User;
import com.example.cinema.repository.RoleRepository;
import com.example.cinema.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {
    @PersistenceContext
    private EntityManager em;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder ;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }

    public User findUserByName(String username) {
        User userFromDb = userRepository.findByUsername(username);
        return userFromDb;
    }

    public User findUserById(Long userId) {
        Optional<User> userFromDb = userRepository.findById(userId);
        return userFromDb.orElse(new User());
    }

    public List<User> allUsers() {
        return userRepository.findAll();
    }


    public User update(User user){
        User userFromDB = userRepository.findById(user.getId()).orElseThrow(EntityNotFoundException::new);
        userFromDB.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userFromDB.setPasswordConfirm(bCryptPasswordEncoder.encode(user.getPassword()));
        userFromDB.setEmail(user.getEmail());
        userFromDB.setUsername(user.getUsername());
        userRepository.save(userFromDB);
        return userFromDB;
    }

    public boolean saveUser(User user) {
        User userFromDB = userRepository.findByUsername(user.getUsername());
        if (userFromDB != null) {
            return false;
        }

        user.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(Long userId) {
        if (userRepository.findById(userId).isPresent()) {
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    public List<User> usergtList(Long idMin) {
        return em.createQuery("SELECT u FROM User u WHERE u.id > :paramId", User.class)
                .setParameter("paramId", idMin).getResultList();
    }


    public boolean checkPasswordByEqual(String strPassword, String bCryptPassword){
        boolean isPasswordChanged = bCryptPasswordEncoder.matches(strPassword,bCryptPassword);
        return isPasswordChanged;
    }

    public User editUsernameEmail(User user){
        User userFromDB = userRepository.findById(user.getId()).orElseThrow(EntityNotFoundException::new);

        String s = bCryptPasswordEncoder.encode(user.getPassword());
        boolean isPasswordChanged = bCryptPasswordEncoder.matches(user.getPassword(),userFromDB.getPassword());

        if (!isPasswordChanged){
            return null;
        }
            userFromDB.setEmail(user.getEmail());
            userFromDB.setUsername(user.getUsername());
            userRepository.save(userFromDB);
            return userFromDB;
    }

    public User editPassword(User user) {
        User userFromDB = userRepository.findById(user.getId()).orElseThrow(EntityNotFoundException::new);

        boolean isPasswordChanged = bCryptPasswordEncoder.matches(user.getEmail(),userFromDB.getPassword());

        if (!isPasswordChanged){
            return null;
        }
        userFromDB.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(userFromDB);
        return userFromDB;
    }

    public boolean checkEmail(String email) {
       if(userRepository.existsByEmail(email)){
           return false;
       }
       return true;
    }

    public boolean checkName(String username) {
        if(userRepository.existsByUsername(username)){
            return false;
        }
        return true;
    }
}
