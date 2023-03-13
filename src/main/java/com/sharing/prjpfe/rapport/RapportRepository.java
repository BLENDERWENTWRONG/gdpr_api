package com.sharing.prjpfe.rapport;

import com.sharing.prjpfe.document.Document;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RapportRepository extends JpaRepository<Rapport,Long> {
    public List<Rapport> findAllByTache_Id(Long id_tache);
}
