package com.sharing.prjpfe.rapport;

import com.sharing.prjpfe.document.Document;
import com.sharing.prjpfe.tache.Tache;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@Table
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rapport {
    @Id
    //@SequenceGenerator(name="document_sequence",sequenceName ="document_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRapport;
    private LocalDate dateRapport;
    private String resultatRapport;

    private String commentaire;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idtache")
    private Tache tache;

    public Rapport(LocalDate dateRapport, String resultatRapport, Tache tache) {
        this.dateRapport = dateRapport;
        this.resultatRapport = resultatRapport;
        this.tache = tache;
    }
    public Rapport(LocalDate dateRapport, String resultatRapport,String commentaire, Tache tache) {
        this.dateRapport = dateRapport;
        this.resultatRapport = resultatRapport;
        this.tache = tache;
        this.commentaire=commentaire;
    }
}
