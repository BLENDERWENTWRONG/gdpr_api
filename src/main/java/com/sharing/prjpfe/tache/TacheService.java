package com.sharing.prjpfe.tache;

import com.sharing.prjpfe.document.Document;
import com.sharing.prjpfe.document.DocumentService;
import com.sharing.prjpfe.rapport.Rapport;
import com.sharing.prjpfe.rapport.RapportService;

import com.sharing.prjpfe.twilio.SmsSender;
import com.sharing.prjpfe.utils.PdfGenerator;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TacheService {
    @Autowired
    SmsSender smsSender;
    @Autowired
    PdfGenerator pdf;

    Document doc;
    @Autowired
    private TacheRepository rep;
    @Autowired
    RapportService rap;
    @Autowired
    DocumentService fil;
    @Transactional
    public List<Tache> getAllTache(){
        List<Tache> taches = new ArrayList<Tache>();
        rep.findAll().forEach(tache -> taches.add(tache));
        return taches;
    }
    @Transactional
    public Optional<Tache> getTacheById(Long id){
        return rep.findById(id);
    }
    public void addTache(Tache tache) {
        rep.save(tache);
    }
    public void addAllTache(List<Tache> taches) {
        rep.saveAll(taches);
    }
    public List<Tache> findTachesByEtat(String etatTache){
        System.out.println(rep.findTachesByEtatTache(etatTache).get(0));
        return rep.findTachesByEtatTache(etatTache);
   }
    @Transactional
    public List<Tache> findTachesByDate(LocalDate date){
        return rep.findTachesByDateAllocation(date);
    }
    @Transactional
    public void terminateAccessTaskSucces(String t,Long id,String res,List<MultipartFile> files) throws IOException {
        Tache tache = getTacheById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found with id: " + id));
        tache.setEtatTache(t);
        if (tache.getEtatTache().equals("termine")){

        addTache(tache);



        Rapport r=new Rapport(LocalDate.now(),res,tache);

            for(int i=0;i< files.size();i++){
                fil.UploadPdf(files.get(i),tache.getId());
            }
            List<String> names = fil.allNomFichier(tache.getId());
            rap.addRapport(r);
            byte[] rapport =pdf.pdfRapportSuccesAccess("credit",tache.getId(),tache.getDescription(),"acces","adib baldi",res, names);

            fil.UploadGeneratedPdf(rapport,r.getIdRapport(), tache.getId());
        }
        }
      public void terminateTaskFailure(String t,Long id,String res , String comment) throws IOException {
          Tache tache = getTacheById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "tache not found with id: " + id));
          tache.setEtatTache(t);
          if (tache.getEtatTache().equals("termine")) {

              addTache(tache);
              Rapport r = new Rapport(LocalDate.now(), res, tache);
              rap.addRapport(r);
              byte[] rapport =pdf.pdfRapportrefuse("credit",tache.getId(),tache.getDescription(),"modification","adib baldi",res, comment);
              fil.UploadGeneratedPdf(rapport,r.getIdRapport(), tache.getId());

              //smsSender.sendSms(new SmsRequest("+21621596006","votre tache est annulé malheureusement"));
          }
      }
      public  void terminateTaskSucces(String t,Long id,String res) throws IOException {
          Tache tache = getTacheById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "tache not found with id: " + id));
          tache.setEtatTache(t);
          if (tache.getEtatTache().equals("termine")) {

              addTache(tache);
              Rapport r = new Rapport(LocalDate.now(), res, tache);
              rap.addRapport(r);
              byte[] rapport =pdf.pdfRapportSucces("credit",tache.getId(),tache.getDescription(),"modification","adib baldi",res);
              fil.UploadGeneratedPdf(rapport,r.getIdRapport(), tache.getId());
          }
      }



    @Transactional
    public List<Tache> findTachesByDateAndEtat(String etat,LocalDate date){
        return rep.findTachesByEtatTacheAndAndDateAllocation(etat,date);

    }
}
