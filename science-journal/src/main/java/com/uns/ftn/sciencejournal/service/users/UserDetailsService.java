package com.uns.ftn.sciencejournal.service.users;

import com.uns.ftn.sciencejournal.model.PaperSearchModel;
import com.uns.ftn.sciencejournal.model.users.UserDetails;
import com.uns.ftn.sciencejournal.repository.users.UserDetailsRepository;
import com.uns.ftn.sciencejournal.service.utils.GoogleCoordinatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsService {

    @Autowired
    UserDetailsRepository userDetailsRepository;

    public UserDetails getById(Long id) {
        return userDetailsRepository.findById(id).orElse(null);
    }

    public List<UserDetails> getAll() {
        return userDetailsRepository.findAll();
    }

    public UserDetails createUser(UserDetails userDetails) {

        if (userDetails.getUserId() != null) {
            return null;
        }

        if (!checkUserValidity(userDetails)) {
            return null;
        }

        userDetails = setCoordinatesFromAddress(userDetails);

        return userDetailsRepository.save(userDetails);
    }

    public UserDetails updateUser(UserDetails newUserDetails, Long id) {

        if (id == null) {
            return null;
        }

        UserDetails userDetails = getById(id);
        if (userDetails == null) {
            return null;
        }

        if (!checkUserValidity(newUserDetails)) {
            return null;
        }

        userDetails.setCity(newUserDetails.getCity());
        userDetails.setCountry(newUserDetails.getCountry());
        userDetails.setEmail(newUserDetails.getEmail());
        userDetails.setfName(newUserDetails.getfName());
        userDetails.setlName(newUserDetails.getlName());
        userDetails = setCoordinatesFromAddress(userDetails);

        return userDetailsRepository.save(userDetails);
    }

    private boolean checkUserValidity(UserDetails userDetails) {
        if (userDetails.getfName() == null || userDetails.getfName().equals("")) {
            return false;
        }

        if (userDetails.getlName() == null || userDetails.getlName().equals("")) {
            return false;
        }

        if (userDetails.getCity() == null || userDetails.getCity().equals("")) {
            return false;
        }

        if (userDetails.getCountry() == null || userDetails.getCountry().equals("")) {
            return false;
        }

        if (userDetails.getEmail() == null || userDetails.getEmail().equals("")) {
            return false;
        }

        return true;
    }

    private UserDetails setCoordinatesFromAddress(UserDetails userDetails){
        GoogleCoordinatesService service = new GoogleCoordinatesService();
        PaperSearchModel.Location location = service.getCoordinatesFromAddress(userDetails.getCity(), userDetails.getCountry());
        userDetails.setLatitude(Double.parseDouble(location.getLat()));
        userDetails.setLongitude(Double.parseDouble(location.getLon()));
        return userDetails;
    }

    public void deleteUser(Long id) {
        if (id != null) {
            userDetailsRepository.deleteById(id);
        }
    }


}
