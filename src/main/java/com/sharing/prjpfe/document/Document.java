package com.sharing.prjpfe.document;

import com.sharing.prjpfe.rapport.Rapport;
import com.sharing.prjpfe.tache.Tache;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Document {
    @Id
    //@SequenceGenerator(name="document_sequence",sequenceName ="document_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDocument;
    private String documentNom;
    private String documentType;
    @Lob
    @Column(length = 50000000)
    private byte[] data;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idrapport")
    private Rapport rapport;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idtache")
    private Tache tache;

    public Document(Long idDocument, String documentNom, String documentType, byte[] data, Tache tache) {
        this.idDocument = idDocument;
        this.documentNom = documentNom;
        this.documentType = documentType;
        this.data = data;
        this.tache = tache;
    }



    public Document(Long idDocument, String documentNom, String documentType, byte[] data, Rapport rapport) {
        this.idDocument = idDocument;
        this.documentNom = documentNom;
        this.documentType = documentType;
        this.data = data;
        this.rapport = rapport;
    }
}
