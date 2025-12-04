package model;

import java.io.Serializable;

public class Leverandør implements Serializable {
    private String navn;
    private String email;
    private String telefonnummer;
    private String adresse;

    public Leverandør(String navn, String email, String telefonnummer, String adresse) {
        this.navn = navn;
        this.email = email;
        this.telefonnummer = telefonnummer;
        this.adresse = adresse;
    }

    public String getNavn() {
        return navn;
    }

    public String getEmail() {
        return email;
    }

    public String getTelefonnummer() {
        return telefonnummer;
    }

    public String getAdresse() {
        return adresse;
    }

    @Override
    public String toString() {
        return "Leverandør: " +  navn + ", " + email;
    }
}