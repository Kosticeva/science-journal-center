package com.uns.ftn.sciencejournal.repository.common;

import com.uns.ftn.sciencejournal.model.common.ScienceField;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScienceFieldRepository extends JpaRepository<ScienceField, String> {
}
