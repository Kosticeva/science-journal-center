package com.uns.ftn.sciencejournal.repository.users;

import com.uns.ftn.sciencejournal.model.users.UserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetails, Long> {

    Optional<UserDetails> findFirstByEmail(String email);
}
