package com.example.films;

public class Film {
    private Integer rang;
    private String id;
    private String nom;
    private Integer annee;
    private String acteur;

    public Film() {

    }

    public int getRang() {
        return rang ;
    }

    public void setRang(Integer rang) {
        this.rang = rang;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNom() {
        return nom ;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(Integer annee) {
        this.annee = annee;
    }

    public String getActeur() {
        return acteur;
    }

    public void setActeur(String acteur) {
        this.acteur = acteur;
    }

}
