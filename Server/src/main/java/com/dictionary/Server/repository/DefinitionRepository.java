package com.dictionary.Server.repository;

import com.dictionary.Server.models.Definition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface DefinitionRepository extends JpaRepository<Definition, Long> {
    Definition findDefinitionById(Long id);
    List<Definition> findDefinitionByTerm(String term);
}
