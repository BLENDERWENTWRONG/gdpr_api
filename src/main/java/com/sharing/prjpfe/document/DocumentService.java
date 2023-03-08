package com.sharing.prjpfe.document;
import com.sharing.prjpfe.rapport.Rapport;
import com.sharing.prjpfe.rapport.RapportRepository;
import com.sharing.prjpfe.tache.TacheRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.print.Doc;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DocumentService {
    @Autowired
    DocumentRepository rep;
    @Autowired
    RapportRepository rrep;
    @Autowired
    TacheRepository trep;
    @Transactional
    public String UploadPdf(MultipartFile file,Long id) throws IOException, MalformedURLException {
        Document doc = rep.save(Document.builder().documentNom(file.getOriginalFilename()).documentType(file.getContentType()).data(DocumentUtils.compressFile(file.getBytes())).tache(trep.findById(id).get()).build());
        if(doc!=null){
            return "document ajouté avec succes ";
        }
        return null;
    }
    public void UploadGeneratedPdf(byte[] blob,Long idTache,Long idRapport) throws IOException, MalformedURLException {
        Document doc = rep.save(Document.builder().documentNom("rapport.pdf").documentType("application/pdf").data(DocumentUtils.compressFile(blob)).rapport(rrep.findById(idRapport).get()).tache(trep.findById(idTache).get()).build());
    }
    @Transactional
    public List<String> allNomFichier(Long id){
        return rep.findDocumentNom(id);
    }
    @Transactional
    public byte[] download(Long id){
        Optional<Document> doc=rep.findById(id);
        return DocumentUtils.decompressFile(doc.get().getData());
    }
    @Transactional
    public List<byte[]> docByTache(Long id){
        List<Document> docs= rep.findDocumentByTach(id);
        List<byte[]> bytes=new ArrayList<>();
        for(Document doc:docs){
            bytes.add(DocumentUtils.decompressFile(doc.getData()));
        }
        return bytes;
    }
    @Transactional
    public List<byte[]> docByRap(Long id){
        List<Document> docs= rep.findDocumentByRap(id);
        List<byte[]> bytes=new ArrayList<>();
        for(Document doc:docs){
            bytes.add(DocumentUtils.decompressFile(doc.getData()));
        }
        return bytes;
    }
}
