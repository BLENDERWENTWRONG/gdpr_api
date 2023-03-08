package com.sharing.prjpfe.tache;


import com.sharing.prjpfe.document.Document;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Tache {
    @Id
    //@SequenceGenerator(name="tache_sequence",sequenceName ="tache_sequence",allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="dateallocation")
    private LocalDate dateAllocation;
    @Column(name="etattache")
    private String etatTache;
    @Column
    private String description;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "idtache")
    private List<Document> docs;

    public Tache() {

    }

    public Tache(Long id, LocalDate dateAllocation, String etatTache, String description) {
        this.id = id;
        this.dateAllocation = dateAllocation;
        this.etatTache = etatTache;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getDateAllocation() {
        return dateAllocation;
    }

    public String getEtatTache() {
        return etatTache;
    }

    public String getDescription() {
        return description;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setDate_Alocation(LocalDate dateAllocation) {
        this.dateAllocation = dateAllocation;
    }

    public void setEtatTache(String etatTache) {
        this.etatTache = etatTache;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
