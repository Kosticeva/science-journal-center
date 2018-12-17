package com.uns.ftn.sciencejournal.service.users;

import com.uns.ftn.sciencejournal.model.users.User;
import com.uns.ftn.sciencejournal.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User createUser(User user) {

        if (user.getUserId() != null) {
            return null;
        }

        if (!checkUserValidity(user)) {
            return null;
        }

        return userRepository.save(user);
    }

    public User updateUser(User newUser, Long id) {

        if (id == null) {
            return null;
        }

        User user = getById(id);
        if (user == null) {
            return null;
        }

        if (!checkUserValidity(newUser)) {
            return null;
        }

        user.setCity(newUser.getCity());
        user.setCountry(newUser.getCountry());
        user.setEmail(newUser.getEmail());
        user.setfName(newUser.getfName());
        user.setlName(newUser.getlName());

        return userRepository.save(user);
    }

    private boolean checkUserValidity(User user) {
        if (user.getfName() == null || user.getfName().equals("")) {
            return false;
        }

        if (user.getlName() == null || user.getlName().equals("")) {
            return false;
        }

        if (user.getCity() == null || user.getCity().equals("")) {
            return false;
        }

        if (user.getCountry() == null || user.getCountry().equals("")) {
            return false;
        }

        if (user.getEmail() == null || user.getEmail().equals("")) {
            return false;
        }

        return true;
    }

    public void deleteUser(Long id) {
        if (id != null) {
            userRepository.deleteById(id);
        }
    }


}
