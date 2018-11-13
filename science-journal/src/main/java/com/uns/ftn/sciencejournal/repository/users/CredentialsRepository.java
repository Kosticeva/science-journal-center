package com.uns.ftn.sciencejournal.repository.users;

import com.uns.ftn.sciencejournal.model.users.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredentialsRepository extends JpaRepository<Credentials, String> {
}
