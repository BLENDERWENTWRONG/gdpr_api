package com.sharing.prjpfe.document;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping(path = "/document")
public class DocumentController {
    @Autowired
    DocumentService serv;
    @PostMapping
    public ResponseEntity<?> uploadFile(@RequestParam("pdf")MultipartFile file,@RequestParam("idtache")Long id) throws IOException {
    String res = serv.UploadPdf(file,id);

    return ResponseEntity.status(HttpStatus.OK).body(res);
}
@GetMapping(path = "/{id}")
    public ResponseEntity<?> downloadFile(@PathVariable Long id){
        byte[] doc=serv.download(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("application/pdf")).body(doc);
}
    @GetMapping(path = "findDocName/{id}")
    public List<String> FindAllDocName(@PathVariable Long id){
        return serv.allNomFichier(id);
    }

    @GetMapping(path = "findDocBytask/{id}")
    public ResponseEntity<?> FindAllDocByTask(@PathVariable Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
        return ResponseEntity.ok().headers(headers)
                .contentType(MediaType.valueOf("application/pdf"))
                .body(serv.docByTache(id));
    }
    @GetMapping(path = "findDocByReport/{id}")
    public ResponseEntity<?> FindAllDocByReport(@PathVariable Long id){
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, "application/pdf");
        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.valueOf("application/pdf"))
                .body(serv.docByRap(id));
    }
}
