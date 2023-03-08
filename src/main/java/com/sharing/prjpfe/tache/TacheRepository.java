package com.sharing.prjpfe.tache;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TacheRepository extends JpaRepository<Tache, Long> {

  List<Tache> findTachesByEtatTache(String etatTache);
  List<Tache> findTachesByDateAllocation(LocalDate dateAllocation);
  List<Tache> findTachesByEtatTacheAndAndDateAllocation(String etatTache,LocalDate dateAllocation);
}
