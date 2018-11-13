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

        return userRepository.save(user);
    }

    public User updateUser(User newUser, Long id) {

        if (id == null) {
            return null;
        }

        User user = getById(id);
        if (user != null) {

            return userRepository.save(user);
        }

        return null;
    }

    public void deleteUser(Long id) {
        if (id != null) {
            userRepository.deleteById(id);
        }
    }


}
