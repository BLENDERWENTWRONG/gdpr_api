package com.sharing.prjpfe.document;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.swing.event.DocumentEvent;
import java.util.List;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document,Long> {
     Optional<Document> findDocumentByDocumentNom(String documentNom);
     @Query("select d.documentNom from Document d where d.tache.id=?1")
     List<String> findDocumentNom(Long id);
     @Query("select d from Document d where d.tache.id=?1")
     List<Document> findDocumentByTach(Long id);
     @Query("select d from Document d where d.rapport.idRapport=?1")
     List<Document> findDocumentByRap(Long id);
}
