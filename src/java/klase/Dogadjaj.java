/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klase;

import java.time.LocalDateTime;


/**
 *
 * @author iq skola
 */
public class Dogadjaj implements Model {
    private int id;
    
    private String naziv;
    private String naziv_lokacije;
    //proveriti format mySql i LocalDateTime
    //dodati limit po korisniku
    private LocalDateTime datum_i_vreme;
    private String detalji;
    private String glavna_slika_putanja;
    private String video_putanja;
    private final String table;
    //ostale slike?

    public Dogadjaj() {
        this.table = "dogadjaji";
        this.id = -1;
    }

    public Dogadjaj(String naziv, String naziv_lokacije, LocalDateTime datum_i_vreme, String detalji, String glavna_slika_putanja, String video_putanja) {
        this.naziv = naziv;
        this.naziv_lokacije = naziv_lokacije;
        this.datum_i_vreme = datum_i_vreme;
        this.detalji = detalji;
        this.glavna_slika_putanja = glavna_slika_putanja;
        this.video_putanja = video_putanja;
        this.table = "dogadjaji";
        this.id = -1;
    }


    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getNaziv_lokacije() {
        return naziv_lokacije;
    }

    public void setNaziv_lokacije(String naziv_lokacije) {
        this.naziv_lokacije = naziv_lokacije;
    }

    public LocalDateTime getDatum_i_vreme() {
        return datum_i_vreme;
    }

    public void setDatum_i_vreme(LocalDateTime datum_i_vreme) {
        this.datum_i_vreme = datum_i_vreme;
    }

    public String getDetalji() {
        return detalji;
    }

    public void setDetalji(String detalji) {
        this.detalji = detalji;
    }

    public String getGlavna_slika_putanja() {
        return glavna_slika_putanja;
    }

    public void setGlavna_slika_putanja(String glavna_slika_putanja) {
        this.glavna_slika_putanja = glavna_slika_putanja;
    }

    public String getVideo_putanja() {
        return video_putanja;
    }

    public void setVideo_putanja(String video_putanja) {
        this.video_putanja = video_putanja;
    }

    @Override
    public String getTable() {
        return table;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }
    
    
    
    
    
    
    
    
}
