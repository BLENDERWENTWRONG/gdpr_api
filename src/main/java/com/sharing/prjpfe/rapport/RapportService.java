package com.sharing.prjpfe.rapport;

import com.sharing.prjpfe.tache.Tache;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class RapportService {
    @Autowired
    RapportRepository rep;
    public List<Rapport> getAllRapport(){
        List<Rapport> rapports = new ArrayList<Rapport>();
        rep.findAll().forEach(rapport -> rapports.add(rapport));
        return rapports;
    }
    @Transactional
    public void addRapport(Rapport rapport) {
        rep.save(rapport);
    }
    @Transactional
    public Optional<Rapport> getRapportById(Long id){
        return rep.findById(id);
    }
    @Transactional
    public List<Rapport> getRapportTache(Long id){ return rep.findAllByTache_Id(id);}
}
