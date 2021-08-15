package com.dictionary.Server.repository;

import com.dictionary.Server.models.Definition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface DefinitionRepository extends JpaRepository<Definition, Long> {
    Optional<Definition> findDefinitionById(Long id);
    List<Definition> findDefinitionByTerm(String term);
}
