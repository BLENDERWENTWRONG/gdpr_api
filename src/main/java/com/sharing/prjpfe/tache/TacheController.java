package com.sharing.prjpfe.tache;
import com.sharing.prjpfe.rapport.Rapport;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;



@RestController
@RequestMapping(path = "/tache")
public class TacheController {
    @Autowired
    TacheService tacheService;

    @GetMapping
    public List<Tache> getAllTache(){
        return tacheService.getAllTache();
    }

    @GetMapping(path="/filterByState/{Etat}")
    public List<Tache> getAllTacheByEtat(@PathVariable (value="Etat") String etatTache){
        return tacheService.findTachesByEtat(etatTache);
    }
    @GetMapping(path="/filterByDate/{Etat}")
    public List<Tache> getAllTacheByDate(@PathVariable (value="Etat") LocalDate date){
        return tacheService.findTachesByDate(date);
    }
    @GetMapping(path="/filterByDateAndState/{Etat}&{date}")
    public List<Tache> getAllTacheByDate(@PathVariable (value="Etat")String state,@PathVariable (value="date") LocalDate date){
        return tacheService.findTachesByDateAndEtat(state, date);
    }
    @PostMapping
    public void addTache(@RequestBody Tache tache) {
        tacheService.addTache(tache);
    }
    @PostMapping(path = "/taches")
    public void addTaches(@RequestBody List<Tache> taches) {
        tacheService.addAllTache(taches);
    }
    @PutMapping(path="/finTacheAccesSucces/{id}&{res}", consumes = { "multipart/form-data", "application/json" })
    public void finTacheAccesSucces(@PathVariable (value="id") Long id, @RequestParam("tache") String tache, @RequestParam("files") List<MultipartFile> files, @PathVariable (value="res")  String res) throws IOException {
        tacheService.terminateAccessTaskSucces(tache,id,res,files);
    }
    @PutMapping(path="/finTacheSucces/{id}&{res}", consumes = { "multipart/form-data", "application/json" })
    public void finTacheSucces(@PathVariable (value="id") Long id, @RequestParam("tache") String tache, @RequestParam("files") List<MultipartFile> files, @PathVariable (value="res")  String res) throws IOException {
        tacheService.terminateTaskSucces(tache,id,res);
    }
    @PutMapping(path="/finTacheRefuse/{id}&{res}")
    public void finTacheRefuse(@PathVariable (value="id") Long id, @RequestParam("tache") String tache, @RequestParam("comm") String comm, @PathVariable (value="res")  String res) throws IOException {
        tacheService.terminateTaskFailure(tache,id,res,comm);
    }
}
