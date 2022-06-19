package com.example.nationspring.repository;

import com.example.nationspring.model.Nation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NationRepository extends JpaRepository<Nation, Long> {

    @Query("select n from Nation n where n.language = ?1")
    public List<Nation> nationBylanguage(String language);

    @Query("select distinct n.language from Nation n order by n.language")
    public List<String> allMylanguages();

    @Query("select n from Nation n where n.population > ?1 order by n.population desc")
    public List<Nation> nationByPopulation(int population);

    @Query("select n from Nation n where n.population < ?1 order by n.population desc ")
    public List<Nation> nationMaxPopulation(int population);


}
